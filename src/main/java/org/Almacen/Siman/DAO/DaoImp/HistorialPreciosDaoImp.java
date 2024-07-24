package org.Almacen.Siman.DAO.DaoImp;

import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import org.Almacen.Siman.DAO.IHistorialPreciosDao;
import org.Almacen.Siman.Model.HistorialPrecios;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Named
public class HistorialPreciosDaoImp implements IHistorialPreciosDao {

    @PersistenceContext(name = "YourPU")
    private EntityManager _entityManager;

    @Override
    public List<HistorialPrecios> getAll() {
        return _entityManager.createQuery("SELECT h FROM HistorialPrecios h JOIN FETCH h.precioPorTipoUnidad JOIN FETCH h.responsable ", HistorialPrecios.class).getResultList();
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

    @Override
    public boolean isBeingUsed(int id) {
        var query = _entityManager.createQuery(
                "SELECT COUNT(o) > 0 FROM HistorialPrecios  o WHERE o.precioPorTipoUnidad.id = :id", boolean.class
        );
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public List<HistorialPrecios> findHistorialByProductoAndFechaRange(int productoId, LocalDateTime startDate, LocalDateTime endDate) {
        return _entityManager.createQuery(
                        "SELECT h FROM HistorialPrecios h JOIN FETCH h.precioPorTipoUnidad JOIN FETCH h.precioPorTipoUnidad.producto JOIN FETCH h.precioPorTipoUnidad.tipoUnidad JOIN FETCH h.responsable " +
                                "WHERE h.precioPorTipoUnidad.producto.id = :productoId AND h.precioPorTipoUnidad.tipoUnidad.Abrev = :tipounidad AND h.fechaRegistro BETWEEN :startDate AND :endDate ORDER BY h.fechaRegistro DESC",
                        HistorialPrecios.class)
                .setParameter("productoId", productoId)
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate)
                .setParameter("tipounidad", "UND")
                .getResultList();
    }

    @Override
    public HistorialPrecios obtenerUltimoPrecioAntesDeFecha(int productoId, LocalDateTime fecha) {
        try {
            return _entityManager.createQuery(
                            "SELECT h FROM HistorialPrecios h JOIN FETCH h.precioPorTipoUnidad JOIN FETCH h.precioPorTipoUnidad.producto JOIN FETCH h.responsable " +
                                    "WHERE h.precioPorTipoUnidad.producto.id = :productoId AND h.fechaRegistro <= :fecha ORDER BY h.fechaRegistro DESC",
                            HistorialPrecios.class)
                    .setParameter("productoId", productoId)
                    .setParameter("fecha", fecha)
                    .setMaxResults(1)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }




}
