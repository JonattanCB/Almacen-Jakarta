package org.Almacen.TopAlmacen.DAO;


import org.Almacen.TopAlmacen.Model.Dependencia;

import java.util.List;

public interface IDependenciaDao {
    List<Dependencia> getAll();

    Dependencia getById(int id);

    void create(Dependencia c);

    void update(Dependencia c);

    void delete(Dependencia c);
}
