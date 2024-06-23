package org.Almacen.TopAlmacen.DAO;

import org.Almacen.TopAlmacen.Model.MovimientoStock.UpdateCategoriaDto;
import org.Almacen.TopAlmacen.Model.MovimientoStock;

import java.util.List;

public interface IMovimientoStockDao {
    List<MovimientoStock> getAll();

    MovimientoStock getById(int id);

    void create(MovimientoStock c);

    void update(UpdateCategoriaDto c, int id);

    void delete(int id);
}
