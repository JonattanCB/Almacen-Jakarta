package org.Almacen.TopAlmacen.DAO;

import org.Almacen.TopAlmacen.Model.StockUnidades.UpdateCategoriaDto;
import org.Almacen.TopAlmacen.Model.StockUnidades;

import java.util.List;

public interface IStockUnidadesDao {
    List<StockUnidades> getAll();

    StockUnidades getById(int id);

    void create(StockUnidades c);

    void update(UpdateCategoriaDto c, int id);

    void delete(int id);
}
