package org.Almacen.TopAlmacen.DAO.DaoImp;

import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.Almacen.TopAlmacen.DAO.ITipoUnidadDao;
import org.Almacen.TopAlmacen.Model.TipoUnidad;

import java.util.List;

@Named
public class TipoUnidadDaoImp implements ITipoUnidadDao {
    @PersistenceContext(name = "YourPU")
    private EntityManager _entityManager;

    @Override
    public List<TipoUnidad> getAll() {
        return _entityManager.createQuery("SELECT t from TipoUnidad t", TipoUnidad.class).getResultList();
    }

    @Override
    public TipoUnidad getById(int id) {
        return _entityManager.find(TipoUnidad.class, id);
    }

    @Override
    public TipoUnidad create(TipoUnidad c) {
        _entityManager.persist(c);
        return c;

    }

    @Override
    public TipoUnidad update(TipoUnidad c, int id) {
        var findObj = getById(id);
        if (findObj != null) {
            _entityManager.merge(c);
            return findObj;
        } else {
            return null;
        }
    }

    @Override
    public TipoUnidad delete(int id) {
        var findObj = getById(id);
        if (findObj != null) {
            _entityManager.remove(findObj);
            return findObj;
        } else {
            return null;
        }
    }
}
