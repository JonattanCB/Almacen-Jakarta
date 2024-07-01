package org.Almacen.TopAlmacen.DAO;

import org.Almacen.TopAlmacen.DTO.Usuario.UpdateUsuarioDto;
import org.Almacen.TopAlmacen.Model.Usuario;

import java.util.List;

public interface IUsuarioDao {
    List<Usuario> getAll();

    Usuario getById(int id);

    Usuario create(Usuario c);

    Usuario update(UpdateUsuarioDto u, int id);

    Usuario delete(int id);

    void cambiarMarca(int id, String estado);
}
