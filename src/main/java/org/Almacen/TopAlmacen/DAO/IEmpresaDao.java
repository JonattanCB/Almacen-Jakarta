package org.Almacen.TopAlmacen.DAO;

import org.Almacen.TopAlmacen.DTO.Empresa.UpdateEmpresaDto;
import org.Almacen.TopAlmacen.Model.Empresa;

import java.util.List;

public interface IEmpresaDao {
    List<Empresa> getAll();

    Empresa getById(String NroRUC);

    Empresa create(Empresa c);

    Empresa update(UpdateEmpresaDto c, String NroRuc);

    Empresa delete(String NroRuc);
}
