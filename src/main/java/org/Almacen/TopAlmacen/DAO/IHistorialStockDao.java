package org.Almacen.TopAlmacen.DAO;

import org.Almacen.TopAlmacen.Model.HistorialStock;

import java.time.LocalDateTime;
import java.util.List;

public interface IHistorialStockDao {
    List<HistorialStock> getAll();

    HistorialStock getById(int id);

    HistorialStock add(HistorialStock historialStock);

    List<HistorialStock> findHistorialByProductoAndFechaRange(int productoId, LocalDateTime startDate, LocalDateTime endDate);

}
