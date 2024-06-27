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

    public List<DetalleComprobanteSalida> getAllByComprobanteSalida(int id) {
        return _entityManager.createQuery("SELECT d FROM DetalleComprobanteSalida d WHERE d.comprobanteSalida='id' ", DetalleComprobanteSalida.class).getResultList();
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
        return null;
    }

    @Override
    public DetalleComprobanteSalida update(DetalleComprobanteSalida c, int id) {
        return null;
    }

    @Override
    public DetalleComprobanteSalida delete(int id) {
        return null;
    }
}
