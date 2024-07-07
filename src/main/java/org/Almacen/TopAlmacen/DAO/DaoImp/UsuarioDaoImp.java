package org.Almacen.TopAlmacen.DAO.DaoImp;

import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import org.Almacen.TopAlmacen.DAO.IUsuarioDao;
import org.Almacen.TopAlmacen.DTO.Usuario.UpdateUsuarioDto;
import org.Almacen.TopAlmacen.Mappers.UsuarioMapper;
import org.Almacen.TopAlmacen.Model.Categoria;
import org.Almacen.TopAlmacen.Model.Usuario;
import org.Almacen.TopAlmacen.Util.PasswordUtil;

import java.util.List;

@Named
public class UsuarioDaoImp implements IUsuarioDao {

    @PersistenceContext(name = "YourPU")
    private EntityManager _entityManager;

    @Override
    public List<Usuario> getAll() {
        return _entityManager.createQuery("SELECT u FROM Usuario u order by u.id asc ", Usuario.class).getResultList();
    }

    @Override
    public Usuario getById(int id) {
        var usuario = _entityManager.find(Usuario.class, id);
        _entityManager.detach(usuario);
        return usuario;
    }

    @Override
    public Usuario create(Usuario c) {
        _entityManager.persist(c);
        return c;
    }

    @Override
    public Usuario update(UpdateUsuarioDto u, int id) {
        var existingUsuario = _entityManager.find(Usuario.class, id);
        if (existingUsuario != null) {
            existingUsuario.setCorreo(u.getCorreo());
            existingUsuario.setContra(u.getContra());
            existingUsuario.setNombres(u.getNombres());
            existingUsuario.setApellidos(u.getApellidos());
            existingUsuario.setEstado(u.getEstado());
            existingUsuario.setUnidadDependencia(u.getUnidadDependencia());
            _entityManager.merge(existingUsuario);
            return existingUsuario;
        } else {
            return null;
        }
    }

    @Override
    public Usuario delete(int id) {
        return null;
    }

    @Override
    public void cambiarMarca(int id, String estado) {
        var query = _entityManager.createQuery(
                "UPDATE Usuario u SET u.estado = :estado WHERE u.id = :id"
        );
        query.setParameter("estado", estado);
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public Usuario checkLogin(String login, String password) {
        String hashedPassword = PasswordUtil.hashPassword(password);
        var query = _entityManager.createQuery("SELECT u FROM Usuario u WHERE u.correo = :login AND u.contra = :password", Usuario.class);
        query.setParameter("login", login);
        query.setParameter("password", hashedPassword);
        query.setMaxResults(1);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

}