package org.Almacen.TopAlmacen.DAO;

import org.Almacen.TopAlmacen.DTO.Permiso.UpdatePermisoDto;

import org.Almacen.TopAlmacen.Model.Permiso;

import java.util.List;

public interface IPermisoDao {
    List<Permiso> getAll();

    Permiso getById(int id);

    void create(Permiso c);

    void update(UpdatePermisoDto c, int id);

    void delete(int id);
}
