package org.Almacen.TopAlmacen.Services;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.Almacen.TopAlmacen.DAO.IMovimientoStockDao;
import org.Almacen.TopAlmacen.DAO.IPrecioPorTipoUnidadDao;
import org.Almacen.TopAlmacen.DAO.IStockUnidadesDao;
import org.Almacen.TopAlmacen.Model.MovimientoStock;
import org.Almacen.TopAlmacen.Model.StockUnidades;

import java.util.List;

@Stateless
@LocalBean
public class StockUnidadesService {

    @Inject
    private IStockUnidadesDao istockUnidadesDao;
    @Inject
    private IPrecioPorTipoUnidadDao iprecioPorTipoUnidadDao;
    @Inject
    private IMovimientoStockDao imovimientoStockDao;

    @Transactional
    public List<StockUnidades> getAllStockUnidades() {
        return istockUnidadesDao.getAll();
    }

    @Transactional
    public StockUnidades getStockUnidadesById(int idStockUnidades) {
        return istockUnidadesDao.getById(idStockUnidades);
    }

    @Transactional
    public StockUnidades addStockUnidades(int precioPorTipoUnidadId, double cantidadAgregar) {
        var precioPorTipoUnidad = iprecioPorTipoUnidadDao.getById(precioPorTipoUnidadId);
        if (cantidadAgregar <= 0) {
            throw new IllegalArgumentException("La cantidad a agregar debe ser mayor que cero.");
        }
        if (precioPorTipoUnidad != null) {
            var stockUnidades = istockUnidadesDao.findByProductoAndTipoUnidad(precioPorTipoUnidad.getProducto(), precioPorTipoUnidad.getTipoUnidad().getAbrev());
            stockUnidades.setCantidadStockUnidad(stockUnidades.getCantidadStockUnidad() + cantidadAgregar);
            var ms = new MovimientoStock();
            ms.setTipoMovimiento("ENTRADA");
            ms.setPrecioPorUnidad(precioPorTipoUnidad);
            ms.setCantidad(cantidadAgregar);
            ms.setTipoUnidad(stockUnidades.getTipoUnidad());
            imovimientoStockDao.create(ms);
            return istockUnidadesDao.update(stockUnidades);

        } else {
            throw new IllegalArgumentException("El precio por tipo de unidad con ID " + precioPorTipoUnidadId + " no existe.");
        }
    }

    @Transactional
    public StockUnidades subtractStockUnidades(int precioPorTipoUnidadId, double cantidadARestar) {
        if (cantidadARestar <= 0) {
            throw new IllegalArgumentException("La cantidad a restar debe ser mayor que cero.");
        }
        var precioPorTipoUnidad = iprecioPorTipoUnidadDao.getById(precioPorTipoUnidadId);
        if (precioPorTipoUnidad != null) {
            var stockUnidades = istockUnidadesDao.findByProductoAndTipoUnidad(precioPorTipoUnidad.getProducto(), precioPorTipoUnidad.getTipoUnidad().getAbrev());
            if (stockUnidades != null && verificarStockUnidades(stockUnidades, cantidadARestar)) {
                double cantidadActual = stockUnidades.getCantidadStockUnidad() - cantidadARestar;
                stockUnidades.setCantidadStockUnidad(cantidadActual);
                var ms = new MovimientoStock();
                ms.setTipoMovimiento("SALIDA");
                ms.setPrecioPorTipoUnidad(precioPorTipoUnidad);
                ms.setCantidad(cantidadARestar);
                ms.setTipoUnidad(stockUnidades.getTipoUnidad());
                imovimientoStockDao.create(ms);
                return istockUnidadesDao.update(stockUnidades);
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
