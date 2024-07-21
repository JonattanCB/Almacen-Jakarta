package org.Almacen.TopAlmacen.DAO;

import org.Almacen.TopAlmacen.Model.HistorialPrecios;

import java.time.LocalDateTime;
import java.util.List;

public interface IHistorialPreciosDao {
    List<HistorialPrecios> getAll();

    HistorialPrecios getById(int id);

    HistorialPrecios create(HistorialPrecios c);

    HistorialPrecios delete(int id);

    boolean isBeingUsed(int id);

    List<HistorialPrecios> findHistorialByProductoAndFechaRange(int productoId, LocalDateTime startDate, LocalDateTime endDate);

}
