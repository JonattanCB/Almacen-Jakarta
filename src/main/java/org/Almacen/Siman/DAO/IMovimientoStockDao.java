package org.Almacen.Siman.DAO;

import org.Almacen.Siman.Model.MovimientoStock;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface IMovimientoStockDao {
    List<MovimientoStock> getAll();

    MovimientoStock getById(int id);

    MovimientoStock create(MovimientoStock c);

    MovimientoStock delete(int id);

    List<MovimientoStock> findMovimientosByProductoAndFechaRange(int productoId, LocalDateTime startDate, LocalDateTime endDate);

}
