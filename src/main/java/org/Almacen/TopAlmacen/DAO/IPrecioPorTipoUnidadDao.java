package org.Almacen.TopAlmacen.DAO;

import org.Almacen.TopAlmacen.Model.PrecioPorTipoUnidad.UpdateCategoriaDto;
import org.Almacen.TopAlmacen.Model.PrecioPorTipoUnidad;

import java.util.List;

public interface IPrecioPorTipoUnidadDao {
    List<PrecioPorTipoUnidad> getAll();

    PrecioPorTipoUnidad getById(int id);

    void create(PrecioPorTipoUnidad c);

    void update(UpdateCategoriaDto c, int id);

    void delete(int id);
}
