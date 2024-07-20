package org.Almacen.TopAlmacen.DAO.DaoImp;

import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.Almacen.TopAlmacen.DAO.IRequerimiento_ComprobanteSDao;
import org.Almacen.TopAlmacen.Model.Requerimiento_ComprobanteS;

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
    public Requerimiento_ComprobanteS getById(int id) {
        return _entityManager.find(Requerimiento_ComprobanteS.class, id);
    }

    @Override
    public void add(Requerimiento_ComprobanteS r) {
        _entityManager.persist(r);

    }
}
