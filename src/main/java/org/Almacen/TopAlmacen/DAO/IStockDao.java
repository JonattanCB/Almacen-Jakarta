package org.Almacen.TopAlmacen.DAO;

import org.Almacen.TopAlmacen.Model.Stock;

import java.util.List;

public interface IStockDao {
    List<Stock> getAll();

    Stock getById(int id);

    void create(Stock c);

    void update(Stock c);

    void delete(Stock c);
}
