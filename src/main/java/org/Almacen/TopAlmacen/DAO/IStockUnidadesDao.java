package org.Almacen.TopAlmacen.DAO;

import org.Almacen.TopAlmacen.DTO.StockUnidades.CreateStockUnidadesDto;
import org.Almacen.TopAlmacen.DTO.StockUnidades.UpdateStockUnidadesDto;
import org.Almacen.TopAlmacen.Model.Producto;
import org.Almacen.TopAlmacen.Model.StockUnidades;

import java.util.List;

public interface IStockUnidadesDao {
    List<StockUnidades> getAll();

    StockUnidades getById(int id);

    StockUnidades create(StockUnidades c);

    StockUnidades update(int id, double cantidadAgregar);

    StockUnidades delete(int id);

    StockUnidades findByProductoAndTipoUnidad(Producto producto, String tipoUnidad);

}
