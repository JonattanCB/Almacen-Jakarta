package org.Almacen.Siman.DAO;

import org.Almacen.Siman.Model.UnidadDependencia;

import java.util.List;

public interface IUnidadDependenciaDao {
    List<UnidadDependencia> getAll();

    List<UnidadDependencia> getAllByDependencia(int id);

    UnidadDependencia getById(int id);

    UnidadDependencia create(UnidadDependencia c);

    UnidadDependencia update(UnidadDependencia c, int id);

    UnidadDependencia delete(int id);
}
