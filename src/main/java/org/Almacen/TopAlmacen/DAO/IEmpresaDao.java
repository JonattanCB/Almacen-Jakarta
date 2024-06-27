package org.Almacen.TopAlmacen.DAO;

import org.Almacen.TopAlmacen.DTO.Empresa.UpdateEmpresaDto;
import org.Almacen.TopAlmacen.Model.Empresa;

import java.util.List;

public interface IEmpresaDao {
    List<Empresa> getAll();

    Empresa getById(int id);

    Empresa create(Empresa c);

    Empresa update(UpdateEmpresaDto c, int id);

    Empresa delete(int id);
}
