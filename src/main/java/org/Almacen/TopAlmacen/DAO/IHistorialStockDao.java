package org.Almacen.TopAlmacen.DAO;

import org.Almacen.TopAlmacen.Model.HistorialStock;

import java.util.List;

public interface IHistorialStockDao {
    List<HistorialStock> getAll();

    HistorialStock getById(int id);

    HistorialStock add(HistorialStock historialStock);
}
