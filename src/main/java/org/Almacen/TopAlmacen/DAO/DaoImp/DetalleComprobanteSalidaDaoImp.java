package org.Almacen.TopAlmacen.DAO.DaoImp;

import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.Almacen.TopAlmacen.DAO.IDetalleComprobanteSalidaDao;
import org.Almacen.TopAlmacen.Model.DetalleComprobanteSalida;

import java.util.ArrayList;
import java.util.List;

@Named
public class DetalleComprobanteSalidaDaoImp implements IDetalleComprobanteSalidaDao {

    @PersistenceContext(name = "YourPU")
    private EntityManager _entityManager;

    @Override
    public List<DetalleComprobanteSalida> getAll() {
        return _entityManager.createQuery("SELECT d FROM DetalleComprobanteSalida d", DetalleComprobanteSalida.class).getResultList();
    }

    @Override
    public DetalleComprobanteSalida getById(int id) {
        var item = _entityManager.find(DetalleComprobanteSalida.class, id);
        if (item != null) {
            return item;
        } else {
            return null;
        }

    }

    @Override
    public DetalleComprobanteSalida create(DetalleComprobanteSalida c) {
        _entityManager.persist(c);
        return c;
    }

}
