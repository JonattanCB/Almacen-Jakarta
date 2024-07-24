package org.Almacen.Siman.DAO;

import org.Almacen.Siman.Model.MovimientoStock;

import java.time.LocalDate;
import java.util.List;

public interface IMovimientoStockDao {
    List<MovimientoStock> getAll();

    MovimientoStock getById(int id);

    MovimientoStock create(MovimientoStock c);

    MovimientoStock delete(int id);

    public List<MovimientoStock> findMovimientosByProductoAndFechaRange(int productoId, LocalDate startDate, LocalDate endDate);

}
