package org.Almacen.Siman.DAO;

import org.Almacen.Siman.Model.HistorialStock;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface IHistorialStockDao {
    List<HistorialStock> getAll();

    HistorialStock getById(int id);

    HistorialStock add(HistorialStock historialStock);

    HistorialStock obtenerUltimoStockAntesDeFecha(int productoId, LocalDateTime fecha);

}
