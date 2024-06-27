package org.Almacen.TopAlmacen.DAO;

import jakarta.inject.Named;
import org.Almacen.TopAlmacen.DTO.Categoria.UpdateCategoriaDto;
import org.Almacen.TopAlmacen.Model.Categoria;

import java.util.List;


public interface ICategoriaDao {
    List<Categoria> getAll();

    List<Categoria> getAllByEstadoActivo();

    Categoria getById(int id);

    Categoria create(Categoria c);

    void cambioEstado(int id, String estado);

    Categoria update(UpdateCategoriaDto c, int id);

    Categoria delete(int id);
}
