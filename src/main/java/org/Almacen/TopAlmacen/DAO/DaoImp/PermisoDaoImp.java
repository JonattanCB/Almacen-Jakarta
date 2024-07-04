package org.Almacen.TopAlmacen.DAO.DaoImp;

import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.Almacen.TopAlmacen.DAO.IPermisoDao;
import org.Almacen.TopAlmacen.Model.Permiso;

import java.util.List;

@Named
public class PermisoDaoImp implements IPermisoDao {
    @PersistenceContext(name = "YourPU")
    private EntityManager _entityManager;

    @Override
    public List<Permiso> getAll() {
        return _entityManager.createQuery("SELECT p from Permiso p", Permiso.class).getResultList();
    }

    @Override
    public Permiso getById(int id) {
        return _entityManager.find(Permiso.class, id);
    }

    @Override
    public List<Permiso> findByAccesoId(int accesoId) {
        return _entityManager.createQuery("SELECT p FROM Permiso p WHERE p.acceso.id = :accesoId", Permiso.class)
                .setParameter("accesoId", accesoId)
                .getResultList();
    }
}
