package org.Almacen.TopAlmacen.DAO.DaoImp;

import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import org.Almacen.TopAlmacen.DAO.IStockUnidadesDao;
import org.Almacen.TopAlmacen.DTO.StockUnidades.CreateStockUnidadesDto;
import org.Almacen.TopAlmacen.DTO.StockUnidades.TablaStockUnidadesDto;
import org.Almacen.TopAlmacen.DTO.StockUnidades.UpdateStockUnidadesDto;
import org.Almacen.TopAlmacen.Model.DetalleProductoProveedorEntrada;
import org.Almacen.TopAlmacen.Model.PrecioPorTipoUnidad;
import org.Almacen.TopAlmacen.Model.Producto;
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
    public StockUnidades getByProducto(int id) {
        var query = _entityManager.createQuery(
                "SELECT s FROM StockUnidades s JOIN FETCH  s.producto where s.producto.id = :id", StockUnidades.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public StockUnidades create(StockUnidades c) {
        _entityManager.persist(c);
        return c;
    }

    @Override
    public StockUnidades update(int id, double nuevaCantidad) {
        var obj = _entityManager.find(StockUnidades.class, id);
        if (obj != null) {
            obj.setCantidadStockUnidad(nuevaCantidad);
            _entityManager.merge(obj);
            return obj;
        }
        return null;
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
