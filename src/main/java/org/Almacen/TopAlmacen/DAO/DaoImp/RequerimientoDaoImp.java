package org.Almacen.TopAlmacen.DAO.DaoImp;

import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.Almacen.TopAlmacen.DAO.IRequerimientoDao;
import org.Almacen.TopAlmacen.DTO.Requerimiento.UpdateRequerimientoDto;
import org.Almacen.TopAlmacen.Model.Requerimiento;
import java.util.List;

@Named
public class RequerimientoDaoImp implements IRequerimientoDao {
    @PersistenceContext(name = "YourPU")
    private EntityManager _entityManager;

    @Override
    public List<Requerimiento> getAll() {
        return _entityManager.createQuery("SELECT r FROM Requerimiento r JOIN FETCH r.Requerimientos JOIN FETCH r.unidadDependencia", Requerimiento.class).getResultList();
    }

    @Override
    public Requerimiento getById(int id) {
        return _entityManager.find(Requerimiento.class, id);
    }

    @Override
    public Requerimiento create(Requerimiento c) {
        _entityManager.persist(c);
        return c;
    }

    @Override
    public Requerimiento update(UpdateRequerimientoDto c, int id) {
        var findObj = getById(id);
        if (findObj != null) {
            findObj.setEstado(c.getEstado());
            findObj.setRazonEntrada(c.getRazonEntrada());
            findObj.setRazonSalida(c.getRazonSalida());
            _entityManager.merge(findObj);
            return findObj;
        } else {
            return null;
        }
    }

    @Override
    public Requerimiento delete(int id) {
        var findObj = getById(id);
        if (findObj != null) {
            _entityManager.remove(findObj);
            return findObj;
        } else {
            return null;
        }
    }
}
