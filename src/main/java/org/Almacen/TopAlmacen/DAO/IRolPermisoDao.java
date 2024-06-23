package org.Almacen.TopAlmacen.DAO;

import org.Almacen.TopAlmacen.Model.RolPermiso.UpdateCategoriaDto;
import org.Almacen.TopAlmacen.Model.RolPermiso;

import java.util.List;

public interface IRolPermisoDao {
    List<RolPermiso> getAll();

    RolPermiso getById(int id);

    void create(RolPermiso c);

    void update(UpdateCategoriaDto c, int id);

    void delete(int id);
}
