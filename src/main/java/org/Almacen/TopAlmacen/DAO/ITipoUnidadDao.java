package org.Almacen.TopAlmacen.DAO;

import org.Almacen.TopAlmacen.Model.TipoUnidad;

import java.util.List;

public interface ITipoUnidadDao {
    List<TipoUnidad> getAll();

    TipoUnidad getById(int id);

    void create(TipoUnidad c);

    void update(TipoUnidad c);

    void delete(TipoUnidad c);
}
