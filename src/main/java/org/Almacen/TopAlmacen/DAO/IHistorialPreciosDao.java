package org.Almacen.TopAlmacen.DAO;

import org.Almacen.TopAlmacen.Model.HistorialPrecios.UpdateCategoriaDto;
import org.Almacen.TopAlmacen.Model.HistorialPrecios;

import java.util.List;

public interface IHistorialPreciosDao {
    List<HistorialPrecios> getAll();

    HistorialPrecios getById(int id);

    void create(HistorialPrecios c);

    void update(UpdateCategoriaDto c, int id);

    void delete(int id);
}
