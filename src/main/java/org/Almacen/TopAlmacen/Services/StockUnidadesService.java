package org.Almacen.TopAlmacen.Services;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.Almacen.TopAlmacen.DAO.IMovimientoStockDao;
import org.Almacen.TopAlmacen.DAO.IPrecioPorTipoUnidadDao;
import org.Almacen.TopAlmacen.DAO.IStockUnidadesDao;
import org.Almacen.TopAlmacen.DTO.StockUnidades.StockUnidadesDto;
import org.Almacen.TopAlmacen.DTO.StockUnidades.UpdateStockUnidadesDto;
import org.Almacen.TopAlmacen.Mappers.ProductoMapper;
import org.Almacen.TopAlmacen.Mappers.StockUnidadesMapper;
import org.Almacen.TopAlmacen.Model.MovimientoStock;
import org.Almacen.TopAlmacen.Model.StockUnidades;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
@LocalBean
public class StockUnidadesService implements Serializable {

    @Inject
    private IStockUnidadesDao istockUnidadesDao;
    @Inject
    private IPrecioPorTipoUnidadDao iprecioPorTipoUnidadDao;
    @Inject
    private IMovimientoStockDao imovimientoStockDao;

    @Transactional
    public List<StockUnidadesDto> getAllStockUnidades() {
        var stockUnidades = istockUnidadesDao.getAll();
        return stockUnidades.stream().map(StockUnidadesMapper::toStockUnidadesDto).collect(Collectors.toList());
    }

    @Transactional
    public StockUnidadesDto getStockUnidadesById(int idStockUnidades) {
        var StockUnidad = istockUnidadesDao.getById(idStockUnidades);
        return StockUnidadesMapper.toStockUnidadesDto(StockUnidad);
    }

    @Transactional
    public void addStockUnidades(int precioPorTipoUnidadId, double cantidadAgregar) {
        var precioPorTipoUnidad = iprecioPorTipoUnidadDao.getById(precioPorTipoUnidadId);
        if (cantidadAgregar <= 0) {
            throw new IllegalArgumentException("La cantidad a agregar debe ser mayor que cero.");
        }
        if (precioPorTipoUnidad != null) {
            var stockUnidades = istockUnidadesDao.findByProductoAndTipoUnidad(precioPorTipoUnidad.getProducto(), precioPorTipoUnidad.getTipoUnidad().getAbrev());
            stockUnidades.setCantidadStockUnidad(stockUnidades.getCantidadStockUnidad() + cantidadAgregar);
            var ms = new MovimientoStock();
            ms.setTipoMovimiento("ENTRADA");
            ms.setPrecioPorTipoUnidad(precioPorTipoUnidad);
            ms.setCantidad(cantidadAgregar);
            ms.setTipoUnidad(stockUnidades.getTipoUnidad());
            imovimientoStockDao.create(ms);
            istockUnidadesDao.update(stockUnidades.getId(), cantidadAgregar);

        } else {
            throw new IllegalArgumentException("El precio por tipo de unidad con ID " + precioPorTipoUnidadId + " no existe.");
        }
    }

    @Transactional
    public StockUnidades subtractStockUnidades(int precioPorTipoUnidadId, double cantidadRestar) {
        if (cantidadRestar <= 0) {
            throw new IllegalArgumentException("La cantidad a restar debe ser mayor que cero.");
        }
        var precioPorTipoUnidad = iprecioPorTipoUnidadDao.getById(precioPorTipoUnidadId);
        if (precioPorTipoUnidad != null) {
            var stockUnidades = istockUnidadesDao.findByProductoAndTipoUnidad(precioPorTipoUnidad.getProducto(), precioPorTipoUnidad.getTipoUnidad().getAbrev());
            if (stockUnidades != null && verificarStockUnidades(stockUnidades, cantidadRestar)) {
                double cantidadActual = stockUnidades.getCantidadStockUnidad() - cantidadRestar;
                stockUnidades.setCantidadStockUnidad(cantidadActual);
                var ms = new MovimientoStock();
                ms.setTipoMovimiento("SALIDA");
                ms.setPrecioPorTipoUnidad(precioPorTipoUnidad);
                ms.setCantidad(cantidadRestar);
                ms.setTipoUnidad(stockUnidades.getTipoUnidad());
                imovimientoStockDao.create(ms);
                return istockUnidadesDao.update(stockUnidades.getId(), cantidadRestar);
            } else {
                throw new IllegalStateException("No hay suficiente stock o el stock no existe.");
            }
        } else {
            throw new IllegalArgumentException("El precio por tipo de unidad con ID " + precioPorTipoUnidadId + " no existe.");
        }
    }

    @Transactional
    private boolean verificarStockUnidades(StockUnidades stockUnidades, double cantidadARestar) {
        return stockUnidades.getCantidadStockUnidad() >= cantidadARestar;
    }


}
