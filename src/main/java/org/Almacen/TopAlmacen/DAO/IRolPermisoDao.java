package org.Almacen.TopAlmacen.DAO;

import org.Almacen.TopAlmacen.DTO.RolPermiso.UpdateRolPermisoDto;
import org.Almacen.TopAlmacen.Model.RolPermiso;

import java.util.List;

public interface IRolPermisoDao {
    List<RolPermiso> getAll();

    RolPermiso getById(int id);

    void create(RolPermiso c);

    void update(UpdateRolPermisoDto c, int id);

    void delete(int id);
}
