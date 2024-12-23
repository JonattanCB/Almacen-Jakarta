package org.Almacen.Siman.DAO.DaoImp;

import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.Almacen.Siman.DAO.ITipoUnidadDao;
import org.Almacen.Siman.Model.TipoUnidad;

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

    @Override
    public TipoUnidad findByAbrev(String abrev) {
        return _entityManager.createQuery("SELECT t FROM TipoUnidad t WHERE t.Abrev = :abrev", TipoUnidad.class)
                .setParameter("abrev", abrev)
                .getSingleResult();
    }
}
