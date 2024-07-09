package org.Almacen.TopAlmacen.Services;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.Almacen.TopAlmacen.DAO.IDetalleProductoProveedorEntradaDao;
import org.Almacen.TopAlmacen.DAO.IProductoProveedorEntradaDao;
import org.Almacen.TopAlmacen.DTO.DetalleProductoProveedorEntrada.CreateDetalleProductoProveedorEntradaDto;
import org.Almacen.TopAlmacen.DTO.MovimientoStock.CreateMovimientoStockDto;
import org.Almacen.TopAlmacen.DTO.ProductoProveedorEntrada.CreateProductoProveedorEntradaDto;
import org.Almacen.TopAlmacen.DTO.ProductoProveedorEntrada.ProductoProveedorEntradaDto;
import org.Almacen.TopAlmacen.DTO.ProductoProveedorEntrada.UpdateProductoProveedorEntradaDto;
import org.Almacen.TopAlmacen.DTO.StockUnidades.CreateStockUnidadesDto;
import org.Almacen.TopAlmacen.Mappers.DetalleProductoProveedorEntradaMapper;
import org.Almacen.TopAlmacen.Mappers.ProductoMapper;
import org.Almacen.TopAlmacen.Mappers.ProductoProveedorEntradaMapper;
import org.Almacen.TopAlmacen.Model.MovimientoStock;
import org.Almacen.TopAlmacen.Model.ProductoProveedorEntrada;

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
    private MovimientoStockService movimientoStockService;
    @Inject
    private StockUnidadesService stockUnidadesService;

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

            var totalAAgregar = d.getPrecioPorTipoUnidad().getUnidadesPorTipoUnidadDeProducto() * d.getCantidad();
            stockUnidadesService.addStockUnidades(d.getPrecioPorTipoUnidad(), totalAAgregar);

        }
        return prodcu;
    }

    @Transactional
    public ProductoProveedorEntrada update(UpdateProductoProveedorEntradaDto u, int oc) {
        return iProductoProveedorEntradaDao.update(u, oc);
    }

    @Transactional
    public void delete(int id) {
        iProductoProveedorEntradaDao.delete(id);
    }
}
