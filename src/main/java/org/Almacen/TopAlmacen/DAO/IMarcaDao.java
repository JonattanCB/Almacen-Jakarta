package org.Almacen.TopAlmacen.DAO;

import org.Almacen.TopAlmacen.Model.Marca.UpdateCategoriaDto;
import org.Almacen.TopAlmacen.Model.Marca;

import java.util.List;

public interface IMarcaDao {
    List<Marca> getAll();

    Marca getById(int id);

    void create(Marca c);

    void update(UpdateCategoriaDto c, int id);

    void delete(int id);
}
