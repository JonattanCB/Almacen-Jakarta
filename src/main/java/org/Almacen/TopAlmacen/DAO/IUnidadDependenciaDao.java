package org.Almacen.TopAlmacen.DAO;

import org.Almacen.TopAlmacen.DTO.Categoria.UpdateCategoriaDto;
import org.Almacen.TopAlmacen.Model.UnidadDependencia;

import java.util.List;

public interface IUnidadDependenciaDao {
    List<UnidadDependencia> getAll();

    List<UnidadDependencia> getAllByDependencia(int id);

    UnidadDependencia getById(int id);

    UnidadDependencia create(UnidadDependencia c);

    UnidadDependencia update(UnidadDependencia c, int id);

    UnidadDependencia delete(int id);
}
