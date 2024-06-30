package org.Almacen.TopAlmacen.Services;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.Almacen.TopAlmacen.DAO.IPrecioPorTipoUnidadDao;
import org.Almacen.TopAlmacen.DAO.IStockUnidadesDao;
import org.Almacen.TopAlmacen.Model.PrecioPorTipoUnidad;
import org.Almacen.TopAlmacen.Model.StockUnidades;

import java.util.List;

@Stateless
@LocalBean
public class StockUnidadesService {

    @Inject
    private IStockUnidadesDao istockUnidadesDao;
    @Inject
    private IPrecioPorTipoUnidadDao iprecioPorTipoUnidadDao;

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

        if (precioPorTipoUnidad != null) {
            // Buscar el stock existente por el tipo de unidad y producto
            var stockUnidades = istockUnidadesDao.findByProductoAndTipoUnidad(
                    precioPorTipoUnidad.getProducto(), precioPorTipoUnidad.getTipoUnidad().getAbrev()
            );

            if (stockUnidades != null) {
                stockUnidades.setCantidadStockUnidad(stockUnidades.getCantidadStockUnidad() + cantidadAgregar);
                return istockUnidadesDao.update(stockUnidades);
            } else {
                // Si no existe un stock asociado, crear uno nuevo
                var nuevoStock = new StockUnidades();
                nuevoStock.setCantidadStockUnidad(cantidadAgregar);
                nuevoStock.setTipoUnidad(precioPorTipoUnidad.getTipoUnidad().getAbrev());

                istockUnidadesDao.create(nuevoStock);
                return nuevoStock;
            }
        } else {
            throw new IllegalArgumentException("El precio por tipo de unidad con ID " + precioPorTipoUnidadId + " no existe.");
        }
    }


}
