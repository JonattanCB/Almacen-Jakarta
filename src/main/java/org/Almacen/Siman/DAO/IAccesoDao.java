package org.Almacen.Siman.DAO;

import org.Almacen.Siman.Model.Acceso;

import java.util.List;

public interface IAccesoDao {
    List<Acceso> getAll();

    Acceso getById(int id);

    Acceso create(Acceso c);

    List<Acceso> findByRolId(int rolId);

}
