package org.Almacen.TopAlmacen.DAO.DaoImp;

import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.Almacen.TopAlmacen.DAO.IStockUnidadesDao;
import org.Almacen.TopAlmacen.DTO.StockUnidades.UpdateStockUnidadesDto;
import org.Almacen.TopAlmacen.Model.StockUnidades;

import java.util.List;

@Named
public class StockEnUnidadesDaoImp implements IStockUnidadesDao {
    @PersistenceContext(name = "YourPU")
    private EntityManager _entityManager;

    @Override
    public List<StockUnidades> getAll() {
        return _entityManager.createQuery("SELECT s FROM StockUnidades s", StockUnidades.class).getResultList();
    }

    @Override
    public StockUnidades getById(int id) {
        return _entityManager.find(StockUnidades.class, id);
    }

    @Override
    public StockUnidades create(StockUnidades c) {
        _entityManager.persist(c);
        return c;
    }

    @Override
    public StockUnidades update(UpdateStockUnidadesDto c, int id) {
        var findObj = getById(id);
        if (findObj != null) {
            findObj.setPrecioPorTipoUnidad(c.getPrecioPorTipoUnidad());
            findObj.setCantidadStockUnidad(c.getCantidadStockUnidadesDto());
            findObj.setTipoUnidad(c.getTipoUnidad());
            _entityManager.merge(findObj);
            return findObj;
        } else {
            return null;
        }
    }

    @Override
    public StockUnidades delete(int id) {
        var findObj = getById(id);
        if (findObj != null) {
            _entityManager.remove(findObj);
            return findObj;
        } else {
            return null;
        }
    }
}
