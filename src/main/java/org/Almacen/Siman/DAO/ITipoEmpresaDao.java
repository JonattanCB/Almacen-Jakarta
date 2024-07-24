package org.Almacen.Siman.DAO;

import org.Almacen.Siman.DTO.Empresa.UpdateEmpresaDto;
import org.Almacen.Siman.Model.TipoEmpresa;

import java.util.List;

public interface ITipoEmpresaDao {
    List<TipoEmpresa> getAll();

    TipoEmpresa getById(int id);

    void create(TipoEmpresa c);

    void update(UpdateEmpresaDto c, int id);

    void delete(int id);
}
