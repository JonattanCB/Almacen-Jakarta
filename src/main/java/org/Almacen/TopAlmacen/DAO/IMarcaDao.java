package org.Almacen.TopAlmacen.DAO;

import org.Almacen.TopAlmacen.DTO.Marca.UpdateMarcaDto;
import org.Almacen.TopAlmacen.Model.Marca;

import java.util.List;

public interface IMarcaDao {
    List<Marca> getAll();

    Marca getById(int id);

    Marca create(Marca c);

    Marca update(UpdateMarcaDto c, int id);

    Marca delete(int id);

    void cambiarMarca(int id, String estado);
}
