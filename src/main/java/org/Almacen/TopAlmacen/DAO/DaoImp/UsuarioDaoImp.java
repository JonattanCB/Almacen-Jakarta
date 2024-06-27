package org.Almacen.TopAlmacen.DAO.DaoImp;

import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.Almacen.TopAlmacen.DAO.IUsuarioDao;
import org.Almacen.TopAlmacen.DTO.Usuario.UpdateUsuarioDto;
import org.Almacen.TopAlmacen.Model.Usuario;

import java.util.List;

@Named
public class UsuarioDaoImp implements IUsuarioDao {

    @PersistenceContext(name = "YourPU")
    private EntityManager _entityManager;

    @Override
    public List<Usuario> getAll() {
        return _entityManager.createQuery("SELECT u FROM Usuario u ", Usuario.class).getResultList();
    }

    @Override
    public Usuario getById(int id) {
        var usuario = _entityManager.find(Usuario.class, id);
        _entityManager.detach(usuario);
        return usuario;
    }

    @Override
    public Usuario create(Usuario c) {
        return null;
    }

    @Override
    public Usuario update(UpdateUsuarioDto u) {
        return null;
    }

    @Override
    public Usuario delete(int id) {
        return null;
    }

}