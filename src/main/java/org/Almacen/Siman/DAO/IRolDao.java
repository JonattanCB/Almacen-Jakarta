package org.Almacen.Siman.DAO;

import org.Almacen.Siman.DTO.Rol.UpdateRolDto;
import org.Almacen.Siman.Model.Rol;

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
