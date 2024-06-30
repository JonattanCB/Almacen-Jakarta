package org.Almacen.TopAlmacen.DAO.DaoImp;

import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.Almacen.TopAlmacen.DAO.IRolDao;
import org.Almacen.TopAlmacen.DTO.Rol.UpdateRolDto;
import org.Almacen.TopAlmacen.Model.Categoria;
import org.Almacen.TopAlmacen.Model.Rol;

import java.util.List;

@Named
public class RolDaoImp implements IRolDao {

    @PersistenceContext(name = "YourPU")
    private EntityManager _entityManager;


    @Override
    public List<Rol> getAll() {
        return _entityManager.createQuery("SELECT c FROM Rol c order by c.id asc ", Rol.class).getResultList();
    }

    @Override
    public Rol getById(int id) {
        var query = _entityManager.createQuery(
                "SELECT c FROM Rol c where  c.id = :id ", Rol.class
        );
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public Rol create(Rol c) {
        _entityManager.persist(c);
        return c;
    }

    @Override
    public Rol update(UpdateRolDto c, int id) {
        var existingRol = _entityManager.find(Rol.class, id);
        if (existingRol != null) {
            existingRol.setNombre(c.getNombre());
            existingRol.setEstado(c.getEstado());
            _entityManager.merge(existingRol);
            return existingRol;
        } else {
            return null;
        }
    }


    @Override
    public Rol delete(int id) {
        var rol = _entityManager.find(Rol.class, id);
        if (rol != null) {
            _entityManager.remove(rol);
            return rol;
        } else {
            return null;
        }
    }

    @Override
    public void cambioEstado(int id, String estado) {
        var query = _entityManager.createQuery(
                "UPDATE Rol c SET c.estado = :estado WHERE c.id = :id"
        );
        query.setParameter("estado", estado);
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public List<Rol> getAllByEstadoActivoRols() {
        return _entityManager.createQuery("SELECT c FROM Rol c where c.estado='Activo'", Rol.class).getResultList();
    }
}
