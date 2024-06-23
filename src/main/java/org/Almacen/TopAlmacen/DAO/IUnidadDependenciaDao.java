package org.Almacen.TopAlmacen.DAO;

import org.Almacen.TopAlmacen.DTO.Categoria.UpdateCategoriaDto;
import org.Almacen.TopAlmacen.Model.UnidadDependencia;

import java.util.List;

public interface IUnidadDependenciaDao {
    List<UnidadDependencia> getAll();

    UnidadDependencia getById(int id);

    void create(UnidadDependencia c);

    void update(UpdateCategoriaDto c, int id);

    void delete(int id);
}
