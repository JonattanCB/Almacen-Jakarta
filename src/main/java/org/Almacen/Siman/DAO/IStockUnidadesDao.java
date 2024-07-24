package org.Almacen.Siman.DAO;

import org.Almacen.Siman.Model.StockUnidades;

import java.util.List;

public interface IStockUnidadesDao {
    List<StockUnidades> getAll();

    StockUnidades getById(int id);

    StockUnidades getByProducto(int id);

    StockUnidades create(StockUnidades c);

    StockUnidades update(int id, double nuevaCantidad);

    StockUnidades delete(int id);


}
