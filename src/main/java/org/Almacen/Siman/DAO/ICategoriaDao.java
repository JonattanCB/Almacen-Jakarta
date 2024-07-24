package org.Almacen.Siman.DAO;

import org.Almacen.Siman.DTO.Categoria.UpdateCategoriaDto;
import org.Almacen.Siman.Model.Categoria;

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
