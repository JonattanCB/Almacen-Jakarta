package org.Almacen.TopAlmacen.DAO;

import org.Almacen.TopAlmacen.Model.HistorialStock;

import java.time.LocalDate;
import java.util.List;

public interface IHistorialStockDao {
    List<HistorialStock> getAll();

    HistorialStock getById(int id);

    HistorialStock add(HistorialStock historialStock);

    List<HistorialStock> findHistorialByProductoAndFechaRange(int productoId, LocalDate startDate, LocalDate endDate);

    HistorialStock obtenerUltimoStockAntesDeFecha(int productoId, LocalDate fecha);

}
