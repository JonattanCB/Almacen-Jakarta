package org.Almacen.TopAlmacen.DAO;

import org.Almacen.TopAlmacen.Model.TipoUnidad;

import java.util.List;

public interface ITipoUnidadDao {
    List<TipoUnidad> getAll();

    TipoUnidad getById(int id);

    TipoUnidad create(TipoUnidad c);

    TipoUnidad update(TipoUnidad c,int id);

    TipoUnidad delete(int id);
}
