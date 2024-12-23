package org.Almacen.Siman.DAO;


import org.Almacen.Siman.Model.Dependencia;

import java.util.List;

public interface IDependenciaDao {
    List<Dependencia> getAll();

    List<Dependencia> getAllActivos();

    Dependencia getById(int id);

    Dependencia getByIdDependencia(int idDependencia);

    Dependencia create(Dependencia c);

    Dependencia update(Dependencia c, int id);

    Dependencia delete(int id);

    void cambioestado(String estado, int id);

}
