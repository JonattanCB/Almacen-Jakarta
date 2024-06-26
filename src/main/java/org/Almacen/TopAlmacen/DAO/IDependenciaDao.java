package org.Almacen.TopAlmacen.DAO;


import org.Almacen.TopAlmacen.Model.Dependencia;

import java.util.List;

public interface IDependenciaDao {
    List<Dependencia> getAll();

    Dependencia getById(int id);

    Dependencia create(Dependencia c);

    Dependencia update(Dependencia c, int id);

    Dependencia delete(int id);
}
