package org.Almacen.TopAlmacen.DAO;

import org.Almacen.TopAlmacen.Model.TipoDocumento;

import java.util.List;

public interface ITipoDocumentoDao {
    List<TipoDocumento> getAll();

    TipoDocumento getById(int id);

    void create(TipoDocumento c);

    void update(TipoDocumento c);

    void delete(TipoDocumento c);
}
