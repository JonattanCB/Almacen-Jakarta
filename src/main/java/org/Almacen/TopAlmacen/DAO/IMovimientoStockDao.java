package org.Almacen.TopAlmacen.DAO;

import org.Almacen.TopAlmacen.Model.MovimientoStock;

import java.time.LocalDateTime;
import java.util.List;

public interface IMovimientoStockDao {
    List<MovimientoStock> getAll();

    MovimientoStock getById(int id);

    MovimientoStock create(MovimientoStock c);

    MovimientoStock delete(int id);

    public List<MovimientoStock> findMovimientosByProductoAndFechaRange(int productoId, LocalDateTime startDate, LocalDateTime endDate);

}
