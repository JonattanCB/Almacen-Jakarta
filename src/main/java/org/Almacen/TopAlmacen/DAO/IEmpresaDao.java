package org.Almacen.TopAlmacen.DAO;

import org.Almacen.TopAlmacen.DTO.Empresa.UpdateEmpresaDto;
import org.Almacen.TopAlmacen.Model.Empresa;

import java.util.List;

public interface IEmpresaDao {
    List<Empresa> getAll();

    Empresa getById(int id);

    void create(Empresa c);

    void update(UpdateEmpresaDto c, int id);

    void delete(int id);
}
