package org.Almacen.TopAlmacen.DAO;

import org.Almacen.TopAlmacen.DTO.Empresa.UpdateEmpresaDto;
import org.Almacen.TopAlmacen.Model.TipoEmpresa;

import java.util.List;

public interface ITipoEmpresaDao {
    List<TipoEmpresa> getAll();

    TipoEmpresa getById(int id);

    void create(TipoEmpresa c);

    void update(UpdateEmpresaDto c, int id);

    void delete(int id);
}
