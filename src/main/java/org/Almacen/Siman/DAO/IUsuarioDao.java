package org.Almacen.Siman.DAO;

import org.Almacen.Siman.DTO.Usuario.UpdateUsuarioDto;
import org.Almacen.Siman.Model.Usuario;

import java.util.List;

public interface IUsuarioDao {
    List<Usuario> getAll();

    Usuario getById(int id);

    Usuario create(Usuario c);

    Usuario update(UpdateUsuarioDto u, int id);

    Usuario delete(int id);

    int cantidadUsuarioDependencia(int iddependencia);

    int cantidadUsuariosDependenciaStatus(int iddependencia, String status);

    int cantidadUsuarios();

    int cantidadUsuariosStatus(String status);

    void cambiarEstado(int id, String estado);

    boolean existeEmail(String email);

    Usuario checkLogin(String login, String password);

    void cambiarPasswor( String password, int id);
}
