package org.Almacen.TopAlmacen.DAO;

import org.Almacen.TopAlmacen.DTO.Rol.UpdateRolDto;
import org.Almacen.TopAlmacen.Model.Rol;

import java.util.List;

public interface IRolDao {
    List<Rol> getAll();

    Rol getById(int id);

    Rol create(Rol c);

    Rol update(UpdateRolDto c, int id);

    Rol delete(int id);

    void cambioEstado(int id, String estado);

    List<Rol> getAllByEstadoActivoRols();
}
