package org.Almacen.TopAlmacen.DAO.DaoImp;

import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.Almacen.TopAlmacen.DAO.IDependenciaDao;
import org.Almacen.TopAlmacen.Model.Dependencia;

import java.util.List;

@Named
public class DependenciaDaoImp implements IDependenciaDao {
    @PersistenceContext(name = "YourPU")
    private EntityManager _entityManager;

    @Override
    public List<Dependencia> getAll() {
        return _entityManager.createQuery("SELECT d FROM Dependencia d", Dependencia.class).getResultList();
    }

    @Override
    public Dependencia getById(int id) {
        return _entityManager.find(Dependencia.class, id);
    }

    @Override
    public Dependencia create(Dependencia c) {
        return _entityManager.merge(c);
    }

    @Override
    public Dependencia update(Dependencia c, int id) {
        var dependencia = _entityManager.find(Dependencia.class, id);
        if (dependencia != null) {
            dependencia.setNombre(c.getNombre());
            dependencia.setEstado(c.getEstado());
            return dependencia;
        } else {
            return null;
        }
    }

    @Override
    public Dependencia delete(int id) {
        var finObj = _entityManager.find(Dependencia.class, id);
        if (finObj != null) {
            _entityManager.remove(finObj);
            return finObj;
        } else {
            return null;
        }
    }
}
