package org.Almacen.TopAlmacen.Services;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.Almacen.TopAlmacen.DAO.*;
import org.Almacen.TopAlmacen.DTO.DetalleProductoProveedorEntrada.CreateDetalleProductoProveedorEntradaDto;
import org.Almacen.TopAlmacen.DTO.ProductoProveedorEntrada.CreateProductoProveedorEntradaDto;
import org.Almacen.TopAlmacen.DTO.ProductoProveedorEntrada.ProductoProveedorEntradaDto;
import org.Almacen.TopAlmacen.DTO.ProductoProveedorEntrada.UpdateProductoProveedorEntradaDto;
import org.Almacen.TopAlmacen.Mappers.DetalleProductoProveedorEntradaMapper;
import org.Almacen.TopAlmacen.Mappers.ProductoProveedorEntradaMapper;
import org.Almacen.TopAlmacen.Model.HistorialPrecios;
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
    private StockUnidadesService stockUnidadesService;
    @Inject
    private IStockUnidadesDao iStockUnidadesDao;
    @Inject
    private IPrecioPorTipoUnidadDao iPrecioPorTipoUnidadDao;
    @Inject
    private IHistorialPreciosDao iHistorialPreciosDao;

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
            var pptu = iPrecioPorTipoUnidadDao.getById(d.getPrecioPorTipoUnidad().getId());
            if (pptu.getPrecioUnitario() != detalle.getPrecioUnitario()) {
                var his = new HistorialPrecios();
                his.setPrecioPorTipoUnidad(pptu);
                his.setPrecioRegistro(detalle.getPrecioUnitario());
                iHistorialPreciosDao.create(his);
                iPrecioPorTipoUnidadDao.updatePrecioU(detalle.getPrecioUnitario(), pptu.getId());
            }
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
        var precio = iPrecioPorTipoUnidadDao.getById(id);
        var stock = precio.getStockUnidades();
        if (stock != null) {
            var Stock = stock.getPrecios();
            if (Stock.isEmpty()) {
                // El stock no tiene más asociaciones
                iStockUnidadesDao.delete(stock.getId());
            }
            iPrecioPorTipoUnidadDao.delete(id);
        }
    }
}
