package org.Almacen.TopAlmacen.DAO;

import org.Almacen.TopAlmacen.Model.Empresa.UpdateCategoriaDto;
import org.Almacen.TopAlmacen.Model.Empresa;

import java.util.List;

public interface IEmpresaDao {
    List<Empresa> getAll();

    Empresa getById(int id);

    void create(Empresa c);

    void update(UpdateCategoriaDto c, int id);

    void delete(int id);
}
