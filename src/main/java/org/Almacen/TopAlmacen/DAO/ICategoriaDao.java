package org.Almacen.TopAlmacen.DAO;

import org.Almacen.TopAlmacen.DTO.Categoria.UpdateCategoriaDto;
import org.Almacen.TopAlmacen.Model.Categoria;

import java.util.List;

public interface ICategoriaDao {
    List<Categoria> getAll();

    Categoria getById(int id);

    void create(Categoria c);

    void update(UpdateCategoriaDto c, int id);

    void delete(int id);
}
