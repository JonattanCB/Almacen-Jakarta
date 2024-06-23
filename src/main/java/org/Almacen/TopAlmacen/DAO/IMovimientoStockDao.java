package org.Almacen.TopAlmacen.DAO;

import org.Almacen.TopAlmacen.Model.MovimientoStock;

import java.util.List;

public interface IMovimientoStockDao {
    List<MovimientoStock> getAll();

    MovimientoStock getById(int id);

    void create(MovimientoStock c);

    void delete(int id);
}
