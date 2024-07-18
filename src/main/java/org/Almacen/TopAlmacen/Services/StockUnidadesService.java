package org.Almacen.TopAlmacen.Services;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.Almacen.TopAlmacen.DAO.IMovimientoStockDao;
import org.Almacen.TopAlmacen.DAO.IPrecioPorTipoUnidadDao;
import org.Almacen.TopAlmacen.DAO.IStockUnidadesDao;
import org.Almacen.TopAlmacen.DTO.StockUnidades.StockUnidadesDto;
import org.Almacen.TopAlmacen.DTO.StockUnidades.TablaStockUnidadesDto;
import org.Almacen.TopAlmacen.DTO.StockUnidades.UpdateStockUnidadesDto;
import org.Almacen.TopAlmacen.Mappers.ProductoMapper;
import org.Almacen.TopAlmacen.Mappers.StockUnidadesMapper;
import org.Almacen.TopAlmacen.Model.MovimientoStock;
import org.Almacen.TopAlmacen.Model.PrecioPorTipoUnidad;
import org.Almacen.TopAlmacen.Model.StockUnidades;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
@LocalBean
public class StockUnidadesService implements Serializable {

    @Inject
    private IStockUnidadesDao istockUnidadesDao;


    @Transactional
    public List<TablaStockUnidadesDto> getAllTablaStockUnidadesDto() {
        var stockUnidades = istockUnidadesDao.getAll();
        return stockUnidades.stream().map(StockUnidadesMapper::toTablaStockUnidadesDto).collect(Collectors.toList());
    }

    @Transactional
    public StockUnidades getStockUnidadesById(int idStockUnidades) {
        return istockUnidadesDao.getById(idStockUnidades);
    }

    @Transactional
    public void addStockUnidades(PrecioPorTipoUnidad precioPorTipoUnidad, double cantidadAgregar) {
        if (cantidadAgregar <= 0) {
            throw new IllegalArgumentException("La cantidad a agregar debe ser mayor que cero.");
        }
        if (precioPorTipoUnidad != null) {
            var stockUnidades = precioPorTipoUnidad.getProducto().getStockUnidades();
            if (stockUnidades == null) {
                throw new IllegalArgumentException("El precio por tipo de unidad no está asociado a ningún stock.");
            }
            stockUnidades.setCantidadStockUnidad(stockUnidades.getCantidadStockUnidad() + cantidadAgregar);

            istockUnidadesDao.update(stockUnidades.getId(), stockUnidades.getCantidadStockUnidad());

        } else {
            throw new IllegalArgumentException("El precio por tipo de unidad con ID " + precioPorTipoUnidad.getId() + " no existe.");
        }
    }


    @Transactional
    public StockUnidades subtractStockUnidades(PrecioPorTipoUnidad precioPorTipoUnidad, double cantidadRestar) {
        if (cantidadRestar < 0) {
            throw new IllegalArgumentException("La cantidad a restar debe ser mayor que cero.");
        }

        if (precioPorTipoUnidad != null) {
            var stockUnidades = precioPorTipoUnidad.getProducto().getStockUnidades();
            var resto = stockUnidades.getCantidadStockUnidad() - cantidadRestar;
            System.out.println(resto);
            if (resto < 0) {
                return null;
            } else {
                stockUnidades.setCantidadStockUnidad(resto);
                return istockUnidadesDao.update(stockUnidades.getId(), resto);
            }
        } else {
            throw new IllegalArgumentException("El precio por tipo de unidad con ID " + precioPorTipoUnidad.getId() + " no existe.");
        }
    }

    @Transactional
    public Boolean checkStock(int id, double cantidad, PrecioPorTipoUnidad pptu) {
        var stock = istockUnidadesDao.getByProducto(id);
        var existCant = stock.getCantidadStockUnidad() - (cantidad * pptu.getUnidadesPorTipoUnidadDeProducto());
        if (existCant < 0) {
            return true;
        }
        return false;
    }

    @Transactional
    public String convertStockFaltante(PrecioPorTipoUnidad pptu, double cantidad) {
        var stock = pptu.getProducto().getStockUnidades();
        var cantidadPedida = pptu.getUnidadesPorTipoUnidadDeProducto() * cantidad;
        var cantidadDisponible = stock.getCantidadStockUnidad();
        int unidadSuperior = (int) (cantidadDisponible / cantidadPedida);
        double unidadesRestantes = cantidadDisponible % pptu.getUnidadesPorTipoUnidadDeProducto();

        if (unidadSuperior > 0) {
            return "Stock disponible: " + unidadSuperior + " " +pptu.getTipoUnidad()+" y " + unidadesRestantes + " unidades.";
        } else {
            return "Stock disponible: " + cantidadDisponible + " unidades de " + cantidadPedida + " unidades pedidas.";
        }
    }


}
