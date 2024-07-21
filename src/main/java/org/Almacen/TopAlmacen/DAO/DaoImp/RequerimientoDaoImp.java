package org.Almacen.TopAlmacen.DAO.DaoImp;

import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.Almacen.TopAlmacen.DAO.IRequerimientoDao;
import org.Almacen.TopAlmacen.DTO.Requerimiento.UpdateRequerimientoDto;
import org.Almacen.TopAlmacen.Model.Dependencia;
import org.Almacen.TopAlmacen.Model.Producto;
import org.Almacen.TopAlmacen.Model.Requerimiento;

import java.util.List;

@Named
public class RequerimientoDaoImp implements IRequerimientoDao {
    @PersistenceContext(name = "YourPU")
    private EntityManager _entityManager;

    @Override
    public List<Requerimiento> getAll(int idDepedencia) {
        return _entityManager.createQuery("SELECT r FROM Requerimiento r LEFT JOIN FETCH r.itemsRequerimientos LEFT JOIN FETCH r.solicitante where r.solicitante.unidadDependencia.dependencia.id = :id", Requerimiento.class)
                .setParameter("id", idDepedencia)
                .getResultList();
    }
    @Override
    public List<Requerimiento> getAllFinalized() {
        return _entityManager.createQuery("SELECT r FROM Requerimiento r LEFT JOIN FETCH r.itemsRequerimientos LEFT JOIN FETCH r.solicitante WHERE r.Estado='FINALIZADO'", Requerimiento.class).getResultList();
    }

    @Override
    public List<Requerimiento> getRequerimientoByDependencia() {
        return _entityManager.createQuery(
                        "SELECT r FROM Requerimiento r LEFT JOIN FETCH r.solicitante  LEFT JOIN FETCH r.itemsRequerimientos", Requerimiento.class)
                .getResultList();
    }

    @Override
    public List<Requerimiento> getAllAprobed() {
        return _entityManager.createQuery(
                "SELECT r FROM Requerimiento r LEFT JOIN FETCH r.itemsRequerimientos LEFT JOIN FETCH r.solicitante WHERE r.Estado = 'APROBADO'", Requerimiento.class).getResultList();

    }

    @Override
    public Requerimiento getById(String id) {
        return _entityManager.createQuery(
                        "SELECT r FROM Requerimiento r LEFT JOIN FETCH r.solicitante LEFT JOIN FETCH r.dependencia LEFT JOIN FETCH r.solicitante.unidadDependencia LEFT JOIN FETCH r.itemsRequerimientos  WHERE r.id = :id", Requerimiento.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public Requerimiento create(Requerimiento c) {
        _entityManager.persist(c);
        return c;
    }

    @Override
    public Requerimiento update(UpdateRequerimientoDto c, String id) {
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
    public Requerimiento delete(String id) {
        var findObj = getById(id);
        if (findObj != null) {
            _entityManager.remove(findObj);
            return findObj;
        } else {
            return null;
        }
    }

    @Override
    public void setEstado(String id, String estado, String observacion) {
        var findObj = getById(id);
        findObj.setEstado(estado);
        findObj.setRazonSalida(observacion);
        _entityManager.merge(findObj);
    }
}
