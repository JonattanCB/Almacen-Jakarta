package org.Almacen.Siman.DAO.DaoImp;

import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import org.Almacen.Siman.DAO.IHistorialStockDao;
import org.Almacen.Siman.Model.HistorialStock;

import java.time.LocalDateTime;
import java.util.List;


@Named

public class HistorialStockDaoImp implements IHistorialStockDao {
    @PersistenceContext(name = "YourPU")
    private EntityManager _entityManager;

    @Override
    public List<HistorialStock> getAll() {
        return _entityManager.createQuery("SELECT h from HistorialStock h JOIN FETCH h.stockUnidades", HistorialStock.class).getResultList();
    }

    @Override
    public HistorialStock getById(int id) {
        return _entityManager.find(HistorialStock.class, id);
    }

    @Override
    public HistorialStock add(HistorialStock historialStock) {
        _entityManager.persist(historialStock);
        return historialStock;
    }


    @Override
    public HistorialStock obtenerUltimoStockAntesDeFecha(int productoId, LocalDateTime fecha) {
        try {
            return _entityManager.createQuery(
                            "SELECT h FROM HistorialStock h WHERE h.stockUnidades.producto.id = :productoId AND h.fechaRegistrada <= :fecha ORDER BY h.fechaRegistrada DESC",
                            HistorialStock.class)
                    .setParameter("productoId", productoId)
                    .setParameter("fecha", fecha)
                    .setMaxResults(1)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }




}
