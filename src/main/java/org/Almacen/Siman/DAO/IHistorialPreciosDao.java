package org.Almacen.Siman.DAO;

import org.Almacen.Siman.Model.HistorialPrecios;

import java.time.LocalDate;
import java.util.List;

public interface IHistorialPreciosDao {
    List<HistorialPrecios> getAll();

    HistorialPrecios getById(int id);

    HistorialPrecios create(HistorialPrecios c);

    HistorialPrecios delete(int id);

    boolean isBeingUsed(int id);

    List<HistorialPrecios> findHistorialByProductoAndFechaRange(int productoId, LocalDate startDate, LocalDate endDate);

    HistorialPrecios obtenerUltimoPrecioAntesDeFecha(int productoId, LocalDate fecha);


}
