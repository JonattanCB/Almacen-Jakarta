package org.Almacen.TopAlmacen.DAO;

import org.Almacen.TopAlmacen.DTO.StockUnidades.UpdateStockUnidadesDto;
import org.Almacen.TopAlmacen.Model.StockUnidades;

import java.util.List;

public interface IStockUnidadesDao {
    List<StockUnidades> getAll();

    StockUnidades getById(int id);

    void create(StockUnidades c);

    void update(UpdateStockUnidadesDto c, int id);

    void delete(int id);
}
