package org.Almacen.TopAlmacen.DAO;

import org.Almacen.TopAlmacen.Model.Rol;

import java.util.List;

public interface IRolDao {
    List<Rol> getAll();

    Rol getById(int id);

    void create(Rol c);

    void update(Rol c);

    void delete(Rol c);
}
