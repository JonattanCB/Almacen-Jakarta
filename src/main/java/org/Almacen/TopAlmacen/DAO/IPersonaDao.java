package org.Almacen.TopAlmacen.DAO;


import org.Almacen.TopAlmacen.Model.Persona;

import java.util.List;

public interface IPersonaDao {
    List<Persona> getAll();

    Persona getById(int id);

    void create(Persona p);

    void update(Persona p);

    void delete(Persona p);
}
