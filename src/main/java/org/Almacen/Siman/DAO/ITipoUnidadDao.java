package org.Almacen.Siman.DAO;

import org.Almacen.Siman.Model.TipoUnidad;

import java.util.List;

public interface ITipoUnidadDao {
    List<TipoUnidad> getAll();

    TipoUnidad getById(int id);

    TipoUnidad create(TipoUnidad c);

    TipoUnidad update(TipoUnidad c, int id);

    TipoUnidad delete(int id);

    TipoUnidad findByAbrev(String abrev);
}
