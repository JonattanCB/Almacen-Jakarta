package org.Almacen.TopAlmacen.DAO.DaoImp;

import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.Almacen.TopAlmacen.DAO.IMovimientoStockDao;
import org.Almacen.TopAlmacen.Model.MovimientoStock;

import java.time.LocalDateTime;
import java.util.List;

@Named
public class MovimientoStockDaoImp implements IMovimientoStockDao {
    @PersistenceContext(name = "YourPU")
    private EntityManager _entityManager;

    @Override
    public List<MovimientoStock> getAll() {
        return _entityManager.createQuery("SELECT m FROM MovimientoStock m LEFT JOIN FETCH m.producto LEFT JOIN FETCH m.dependencia LEFT JOIN FETCH m.solicitante_Responsable ", MovimientoStock.class).getResultList();
    }

    @Override
    public MovimientoStock getById(int id) {
        return _entityManager.createQuery("SELECT m FROM MovimientoStock m LEFT  JOIN FETCH m.producto LEFT  JOIN FETCH m.dependencia WHERE m.id= :id ", MovimientoStock.class).setParameter("id", id).getSingleResult();
    }

    @Override
    public MovimientoStock create(MovimientoStock c) {
        _entityManager.persist(c);
        return c;
    }

    @Override
    public MovimientoStock delete(int id) {
        var findObj = _entityManager.find(MovimientoStock.class, id);
        if (findObj != null) {
            _entityManager.remove(findObj);
            return findObj;
        } else {
            return null;
        }
    }

    @Override
    public List<MovimientoStock> findMovimientosByProductoAndFechaRange(int productoId, LocalDateTime startDate, LocalDateTime endDate) {
        LocalDateTime truncatedStartDate = startDate.withNano(0);
        LocalDateTime truncatedEndDate = endDate.withNano(0);
        return _entityManager.createQuery(
                        "SELECT m FROM MovimientoStock m WHERE m.producto.id = :productoId AND m.fechaRegistro BETWEEN :startDate AND :endDate",
                        MovimientoStock.class)
                .setParameter("productoId", productoId)
                .setParameter("startDate", truncatedStartDate)
                .setParameter("endDate", truncatedEndDate)
                .getResultList();
    }

}
