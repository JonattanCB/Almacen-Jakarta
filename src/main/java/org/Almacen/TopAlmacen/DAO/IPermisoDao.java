package org.Almacen.TopAlmacen.DAO;

import org.Almacen.TopAlmacen.DTO.Permiso.UpdatePermisoDto;

import org.Almacen.TopAlmacen.Model.Permiso;

import java.util.List;

public interface IPermisoDao {
    List<Permiso> getAll();

    Permiso getById(int id);

    List<Permiso> findByAccesoId(int accesoId);
}
