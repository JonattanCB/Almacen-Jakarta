package org.Almacen.TopAlmacen.DAO.DaoImp;

import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.Almacen.TopAlmacen.DAO.IAccesoDao;
import org.Almacen.TopAlmacen.Model.Acceso;

import java.util.List;

@Named
public class AccesoDaoImp implements IAccesoDao {
    @PersistenceContext(name = "YourPU")
    private EntityManager _entityManager;

    @Override
    public List<Acceso> getAll() {
        return _entityManager.createQuery("SELECT a FROM Acceso a", Acceso.class).getResultList();
    }

    @Override
    public Acceso getById(int id) {
        return _entityManager.find(Acceso.class, id);
    }

    @Override
    public Acceso create(Acceso c) {
        _entityManager.persist(c);
        return c;
    }

    @Override
    public List<Acceso> findByRolId(int rolId) {
        return _entityManager.createQuery("SELECT a FROM Acceso a WHERE a.rol.id = :rolId", Acceso.class)
                .setParameter("rolId", rolId)
                .getResultList();
    }
}

