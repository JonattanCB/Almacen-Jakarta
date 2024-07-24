package org.Almacen.Siman.Services;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.Almacen.Siman.DAO.*;
import org.Almacen.Siman.DTO.DetalleProductoProveedorEntrada.CreateDetalleProductoProveedorEntradaDto;
import org.Almacen.Siman.DTO.ProductoProveedorEntrada.CreateProductoProveedorEntradaDto;
import org.Almacen.Siman.DTO.ProductoProveedorEntrada.ProductoProveedorEntradaDto;
import org.Almacen.Siman.DTO.ProductoProveedorEntrada.UpdateProductoProveedorEntradaDto;
import org.Almacen.Siman.Mappers.DetalleProductoProveedorEntradaMapper;
import org.Almacen.Siman.Mappers.ProductoProveedorEntradaMapper;
import org.Almacen.Siman.Model.*;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
@LocalBean
public class ProductoProveedorEntradaService implements Serializable {
    @Inject
    private IProductoProveedorEntradaDao iProductoProveedorEntradaDao;
    @Inject
    private IDetalleProductoProveedorEntradaDao iDetalleProductoProveedorEntradaDao;
    @Inject
    private StockUnidadesService stockUnidadesService;
    @Inject
    private IStockUnidadesDao iStockUnidadesDao;
    @Inject
    private IPrecioPorTipoUnidadDao iPrecioPorTipoUnidadDao;
    @Inject
    private IHistorialPreciosDao iHistorialPreciosDao;
    @Inject
    private IMovimientoStockDao iMovimientoStockDao;
    @Inject
    private IHistorialStockDao iHistorialStockDao;

    @Transactional
    public List<ProductoProveedorEntradaDto> findAll() {
        var entradas = iProductoProveedorEntradaDao.getAll();
        return entradas.stream().map(ProductoProveedorEntradaMapper::toDto).collect(Collectors.toList());
    }

    @Transactional
    public ProductoProveedorEntradaDto findById(String id) {
        var entrada = iProductoProveedorEntradaDao.getById(id);
        return ProductoProveedorEntradaMapper.toDto(entrada);
    }

    @Transactional
    public ProductoProveedorEntrada create(CreateProductoProveedorEntradaDto c, List<CreateDetalleProductoProveedorEntradaDto> entradas) {
        var prodcu = ProductoProveedorEntradaMapper.fromCreate(c);
        iProductoProveedorEntradaDao.create(prodcu);
        for (CreateDetalleProductoProveedorEntradaDto d : entradas) {
            var detalle = DetalleProductoProveedorEntradaMapper.fromCreate(d);
            iDetalleProductoProveedorEntradaDao.create(detalle);
        }
        return prodcu;
    }

    @Transactional
    public void insertToBD(ProductoProveedorEntrada c, List<DetalleProductoProveedorEntrada> entradas, Usuario u) {
        iProductoProveedorEntradaDao.setAprobadoPor(c, u);
        for (DetalleProductoProveedorEntrada d : entradas) {
            var pptu = iPrecioPorTipoUnidadDao.getByIdProductoIdTipoUnidad(d.getProducto().getId(), d.getTipoUnidad().getId());
            if (pptu.getPrecioUnitario() != d.getPrecioUnitario()) {
                var hisPre = new HistorialPrecios();
                hisPre.setPrecioPorTipoUnidad(pptu);
                hisPre.setResponsable(c.getUsuario());
                hisPre.setPrecioRegistro(d.getPrecioUnitario());
                iHistorialPreciosDao.create(hisPre);
                iPrecioPorTipoUnidadDao.updatePrecioU(d.getPrecioUnitario(), pptu.getId());
            }

            var totalAAgregar = pptu.getUnidadesPorTipoUnidadDeProducto() * d.getCantidad();
            stockUnidadesService.addStockUnidades(pptu, totalAAgregar);

            var hisStock = new HistorialStock();
            hisStock.setCantidadStock(totalAAgregar);
            hisStock.setStockUnidades(d.getProducto().getStockUnidades());
            iHistorialStockDao.add(hisStock);

            var ms = new MovimientoStock();
            ms.setTipoMovimiento("ENTRADA");
            ms.setProducto(d.getProducto());
            ms.setCantidad(d.getCantidad() * pptu.getUnidadesPorTipoUnidadDeProducto());
            ms.setDependencia(c.getUsuario().getUnidadDependencia().getDependencia());
            ms.setSolicitante_Responsable(d.getOC_id().getUsuario());
            iMovimientoStockDao.create(ms);
        }
        iProductoProveedorEntradaDao.setEstado("FINALIZADO", c.getOC());
    }

    @Transactional
    public void noIsertToBD(ProductoProveedorEntrada c, List<DetalleProductoProveedorEntrada> entradas) {
        for (DetalleProductoProveedorEntrada d : entradas) {
            iDetalleProductoProveedorEntradaDao.delete(d.getId());
        }
        iProductoProveedorEntradaDao.delete(c.getOC());
    }

    @Transactional
    public ProductoProveedorEntrada update(UpdateProductoProveedorEntradaDto u, int oc) {
        return iProductoProveedorEntradaDao.update(u, oc);
    }

    @Transactional
    public void cambiarEstado(String estado, String id) {
        iProductoProveedorEntradaDao.setEstado(estado, id);
    }

    @Transactional
    public void delete(String id) {
        iProductoProveedorEntradaDao.delete(id);
    }

    @Transactional
    public int cantiStatus(String status) {
        return iProductoProveedorEntradaDao.cantStatus(status);
    }

}

