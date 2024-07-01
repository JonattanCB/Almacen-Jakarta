package org.Almacen.TopAlmacen.DAO.DaoImp;

import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.Almacen.TopAlmacen.DAO.IUnidadDependenciaDao;
import org.Almacen.TopAlmacen.DTO.Categoria.UpdateCategoriaDto;
import org.Almacen.TopAlmacen.Model.UnidadDependencia;

import java.util.List;

@Named
public class UnidadDaoImp implements IUnidadDependenciaDao {
    @PersistenceContext(name = "YourPU")
    private EntityManager _entityManager;

    @Override
    public List<UnidadDependencia> getAll() {
        return _entityManager.createQuery("SELECT u from UnidadDependencia u", UnidadDependencia.class).getResultList();
    }

    @Override
    public UnidadDependencia getById(int id) {
        return _entityManager.find(UnidadDependencia.class, id);
    }

    @Override
    public UnidadDependencia create(UnidadDependencia c) {
        _entityManager.persist(c);
        return c;
    }

    @Override
    public UnidadDependencia update(UnidadDependencia c, int id) {
        if (getById(id) != null) {
            return _entityManager.merge(c);
        } else {
            return null;
        }
    }

    @Override
    public UnidadDependencia delete(int id) {
        var findObj = _entityManager.find(UnidadDependencia.class, id);
        if (findObj != null) {
            _entityManager.remove(findObj);
            return findObj;
        } else {
            return null;
        }
    }

}