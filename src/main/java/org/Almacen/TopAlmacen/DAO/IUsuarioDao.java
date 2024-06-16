package org.Almacen.TopAlmacen.DAO;

import org.Almacen.TopAlmacen.Model.Usuario;

import java.util.List;

public interface IUsuarioDao {
    List<Usuario> getAll();

    Usuario getById(int id);

    void create(Usuario c);

    void update(Usuario c);

    void delete(Usuario c);
}
