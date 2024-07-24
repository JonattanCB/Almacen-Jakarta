package org.Almacen.Siman.DAO.DaoImp;

import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import org.Almacen.Siman.DAO.IUsuarioDao;
import org.Almacen.Siman.DTO.Usuario.UpdateUsuarioDto;
import org.Almacen.Siman.Model.Usuario;
import org.Almacen.Siman.Util.PasswordUtil;

import java.util.List;

@Named
public class UsuarioDaoImp implements IUsuarioDao {

    @PersistenceContext(name = "YourPU")
    private EntityManager _entityManager;

    @Override
    public List<Usuario> getAll() {
        return _entityManager.createQuery("SELECT u FROM Usuario u LEFT JOIN FETCH u.unidadDependencia " +
                "LEFT JOIN FETCH u.unidadDependencia.dependencia LEFT JOIN FETCH u.unidadDependencia.rol order by u.id asc ", Usuario.class).getResultList();
    }

    @Override
    public Usuario getById(int id) {
        var query = _entityManager.createQuery(
                "SELECT u FROM Usuario u " +
                        "LEFT JOIN FETCH u.unidadDependencia " +
                        "LEFT JOIN FETCH u.unidadDependencia.dependencia " +
                        "LEFT JOIN FETCH u.unidadDependencia.rol " +
                        "WHERE u.id = :id", Usuario.class);
        query.setParameter("id", id);
        return query.getSingleResult();
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
            existingUsuario.setUnidadDependencia(u.getUnidad());
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
    public void cambiarEstado(int id, String estado) {
        var query = _entityManager.createQuery(
                "UPDATE Usuario u SET u.estado = :estado WHERE u.id = :id"
        );
        query.setParameter("estado", estado);
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public boolean existeEmail(String email) {
        Long count = _entityManager.createQuery(
                        "SELECT COUNT(u) FROM Usuario u WHERE u.correo = :email", Long.class)
                .setParameter("email", email)
                .getSingleResult();
        return count > 0;
    }

    @Override
    public Usuario checkLogin(String login, String password) {
        String hashedPassword = PasswordUtil.hashPassword(password);
        var query = _entityManager.createQuery("SELECT u FROM Usuario u LEFT JOIN FETCH u.unidadDependencia LEFT JOIN FETCH u.unidadDependencia.rol LEFT JOIN FETCH u.unidadDependencia.dependencia  " +
                " WHERE u.correo = :login AND u.contra = :password", Usuario.class);
        query.setParameter("login", login);
        query.setParameter("password", hashedPassword);
        query.setMaxResults(1);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void cambiarPasswor(String password, int id) {
        var query = _entityManager.createQuery(
                "UPDATE Usuario u SET u.contra = :contra WHERE u.id = :id"
        );
        query.setParameter("contra", password);
        query.setParameter("id", id);
        query.executeUpdate();
    }

}