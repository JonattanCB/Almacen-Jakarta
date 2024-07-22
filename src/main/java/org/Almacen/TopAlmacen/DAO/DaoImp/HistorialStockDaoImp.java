package org.Almacen.TopAlmacen.DAO.DaoImp;

import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.Almacen.TopAlmacen.DAO.IHistorialStockDao;
import org.Almacen.TopAlmacen.Model.HistorialPrecios;
import org.Almacen.TopAlmacen.Model.HistorialStock;

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
    public List<HistorialStock> findHistorialByProductoAndFechaRange(int productoId, LocalDateTime startDate, LocalDateTime endDate) {
        LocalDateTime truncatedStartDate = startDate.withNano(0);
        LocalDateTime truncatedEndDate = endDate.withNano(0);
        return _entityManager.createQuery("SELECT h FROM HistorialStock h WHERE h.stockUnidades.producto=:productoId  AND h.fechaRegistrada BETWEEN :startDate AND :endDate", HistorialStock.class)
                .setParameter("productoId", productoId)
                .setParameter("startDate", truncatedStartDate)
                .setParameter("endDate", truncatedEndDate)
                .getResultList();
    }
}
