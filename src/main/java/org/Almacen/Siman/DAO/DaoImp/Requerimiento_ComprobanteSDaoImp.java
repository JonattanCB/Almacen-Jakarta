package org.Almacen.Siman.DAO.DaoImp;

import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.Almacen.Siman.DAO.IRequerimiento_ComprobanteSDao;
import org.Almacen.Siman.Model.ComprobanteSalida;
import org.Almacen.Siman.Model.Requerimiento_ComprobanteS;

import java.util.List;

@Named
public class Requerimiento_ComprobanteSDaoImp implements IRequerimiento_ComprobanteSDao {
    @PersistenceContext(name = "YourPU")
    private EntityManager _entityManager;


    @Override
    public List<Requerimiento_ComprobanteS> getAll() {
        return _entityManager.createQuery("SELECT r FROM Requerimiento_ComprobanteS r JOIN FETCH r.requerimiento JOIN FETCH r.comprobanteSalida", Requerimiento_ComprobanteS.class).getResultList();
    }

    @Override
    public Requerimiento_ComprobanteS getById(String id) {
        return _entityManager.createQuery("SELECT r FROM Requerimiento_ComprobanteS r LEFT JOIN FETCH r.comprobanteSalida  LEFT JOIN FETCH r.requerimiento WHERE r.comprobanteSalida.id= :id",
                Requerimiento_ComprobanteS.class).setParameter("id", id).getSingleResult();
    }

    @Override
    public void add(Requerimiento_ComprobanteS r) {
        _entityManager.persist(r);

    }
}
