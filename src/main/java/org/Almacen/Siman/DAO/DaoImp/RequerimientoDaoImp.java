package org.Almacen.Siman.DAO.DaoImp;

import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.Almacen.Siman.DAO.IRequerimientoDao;
import org.Almacen.Siman.DTO.Requerimiento.UpdateRequerimientoDto;
import org.Almacen.Siman.Mappers.UsuarioMapper;
import org.Almacen.Siman.Model.ComprobanteSalida;
import org.Almacen.Siman.Model.Requerimiento;
import org.Almacen.Siman.Model.Usuario;

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
    public List<Requerimiento> getAllbyStatusPendiente(int idDependencia) {
        return _entityManager.createQuery("SELECT r FROM Requerimiento r LEFT JOIN FETCH r.itemsRequerimientos LEFT JOIN FETCH r.solicitante where r.solicitante.unidadDependencia.dependencia.id = :id and  r.Estado = 'PENDIENTE'", Requerimiento.class)
                .setParameter("id", idDependencia)
                .getResultList();
    }

    @Override
    public Requerimiento setAprobado(String idReq, Usuario usuario) {
        var findobj = getById(idReq);
        if (findobj != null) {
            findobj.setAprobadoPor(UsuarioMapper.toConcatuser(usuario));
            _entityManager.merge(findobj);
            return findobj;
        } else {
            return null;
        }
    }


    @Override
    public List<Requerimiento> getAllByIdDependenciaUser(int idunidad, int idUser) {
        return _entityManager.createQuery("SELECT r FROM Requerimiento r LEFT JOIN FETCH r.itemsRequerimientos LEFT JOIN FETCH r.solicitante where r.solicitante.unidadDependencia.id= :id and r.solicitante.id=:user and r.Estado='PENDIENTE'", Requerimiento.class)
                .setParameter("id", idunidad)
                .setParameter("user", idUser)
                .getResultList();
    }

    @Override
    public int cantidadRequerimientosUserUnidad(int idunidad, int idUser) {
        Long count = _entityManager.createQuery(
                        "SELECT COUNT(r) FROM Requerimiento r " +
                                "LEFT JOIN r.itemsRequerimientos " +
                                "LEFT JOIN r.solicitante " +
                                "WHERE r.solicitante.unidadDependencia.id = :id " +
                                "AND r.solicitante.id = :user ", Long.class)
                .setParameter("id", idunidad)
                .setParameter("user", idUser)
                .getSingleResult();
        return count.intValue();
    }

    @Override
    public int cantidadRequerimientosDependencia(int idDependencia) {
        Long count = _entityManager.createQuery(
                        "SELECT COUNT(r) FROM Requerimiento r " +
                                "LEFT JOIN r.itemsRequerimientos " +
                                "LEFT JOIN r.solicitante " +
                                "WHERE r.solicitante.unidadDependencia.dependencia.id = :id ", Long.class)
                .setParameter("id", idDependencia)
                .getSingleResult();
        return count.intValue();
    }

    @Override
    public int cantidadRequerimientosUserUnidadStatus(int idunidad, int idUser, String status) {
        Long count = _entityManager.createQuery(
                        "SELECT COUNT(r) FROM Requerimiento r " +
                                "LEFT JOIN r.itemsRequerimientos " +
                                "LEFT JOIN r.solicitante " +
                                "WHERE r.solicitante.unidadDependencia.id = :id " +
                                "AND r.solicitante.id = :user and r.Estado = :status", Long.class)
                .setParameter("id", idunidad)
                .setParameter("user", idUser)
                .setParameter("status", status)
                .getSingleResult();
        return count.intValue();
    }

    @Override
    public int cantidadRequerimientosdependenciaStatus(int iddependencia, String status) {
        Long count = _entityManager.createQuery(
                        "SELECT COUNT(r) FROM Requerimiento r " +
                                "LEFT JOIN r.itemsRequerimientos " +
                                "LEFT JOIN r.solicitante " +
                                "WHERE r.solicitante.unidadDependencia.dependencia.id = :id " +
                                "AND  r.Estado = :status", Long.class)
                .setParameter("id", iddependencia)
                .setParameter("status", status)
                .getSingleResult();
        return count.intValue();
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
