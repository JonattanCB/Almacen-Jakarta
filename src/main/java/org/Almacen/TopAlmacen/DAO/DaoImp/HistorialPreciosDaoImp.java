package org.Almacen.TopAlmacen.DAO.DaoImp;

import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.Almacen.TopAlmacen.DAO.IHistorialPreciosDao;
import org.Almacen.TopAlmacen.Model.HistorialPrecios;

import java.util.List;

@Named
public class HistorialPreciosDaoImp implements IHistorialPreciosDao {
    @PersistenceContext
    private EntityManager _entityManager;

    @Override
    public List<HistorialPrecios> getAll() {
        return _entityManager.createQuery("SELECT h FROM HistorialPrecios h", HistorialPrecios.class).getResultList();
    }

    @Override
    public HistorialPrecios getById(int id) {
        return _entityManager.find(HistorialPrecios.class, id);
    }

    @Override
    public HistorialPrecios create(HistorialPrecios c) {
        _entityManager.persist(c);
        return c;
    }

    @Override
    public HistorialPrecios delete(int id) {
        var findObject = _entityManager.find(HistorialPrecios.class, id);
        if (findObject != null) {
            _entityManager.remove(findObject);
            return findObject;
        } else {
            return null;
        }

    }
}
