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
        return _entityManager.createQuery("SELECT d FROM Dependencia d order by d.id asc", Dependencia.class).getResultList();
    }

    @Override
    public List<Dependencia> getAllActivos() {
        return _entityManager.createQuery("SELECT d FROM Dependencia d where  d.estado= 'Activo'", Dependencia.class).getResultList();
    }

    @Override
    public Dependencia getById(int id) {
        return _entityManager.createQuery(
                        "SELECT d FROM Dependencia d  LEFT JOIN FETCH d.unidades WHERE d.id = :id", Dependencia.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public Dependencia getByIdDependencia(int idDependencia) {
        return _entityManager.createQuery(
                        "SELECT d FROM Dependencia d  LEFT JOIN FETCH d.unidades WHERE d.id = :id", Dependencia.class)
                .setParameter("id", idDependencia)
                .getSingleResult();
    }

    @Override
    public Dependencia create(Dependencia c) {
        _entityManager.persist(c);
        return c;
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

    @Override
    public void cambioestado(String estado, int id) {
        var query = _entityManager.createQuery(
                "UPDATE Dependencia c SET c.estado = :estado WHERE c.id = :id"
        );
        query.setParameter("estado", estado);
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
