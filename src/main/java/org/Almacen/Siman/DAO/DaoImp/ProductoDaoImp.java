package org.Almacen.Siman.DAO.DaoImp;

import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.Almacen.Siman.DAO.IProductoDao;
import org.Almacen.Siman.DTO.Producto.UpdateProductoDto;
import org.Almacen.Siman.Model.Producto;

import java.util.List;

@Named
public class ProductoDaoImp implements IProductoDao {
    @PersistenceContext(name = "YourPU")
    private EntityManager _entityManager;

    @Override
    public List<Producto> getAll() {
        return _entityManager.createQuery("SELECT p FROM Producto p JOIN fetch p.stockUnidades WHERE p.estado ='ACTIVO'", Producto.class).getResultList();
    }

    @Override
    public List<Producto> getAllFalseEstado() {
        return _entityManager.createQuery("SELECT p FROM Producto p JOIN fetch p.stockUnidades WHERE p.estado ='INACTIVO'", Producto.class).getResultList();
    }

    @Override
    public List<Producto> getAllbyProductos() {
        var query = _entityManager.createQuery(
                "SELECT DISTINCT p.producto FROM PrecioPorTipoUnidad p", Producto.class);
        return query.getResultList();
    }

    @Override
    public Producto getById(int id) {
        return _entityManager.find(Producto.class, id);
    }

    @Override
    public Producto create(Producto c) {
        _entityManager.persist(c);
        return c;
    }

    @Override
    public Producto update(UpdateProductoDto u, int id) {
        var findObj = getById(id);
        if (findObj != null) {
            findObj.setNombre(u.getNombre());
            findObj.setColor(u.getColor());
            findObj.setPeso(u.getPeso());
            findObj.setCategoria(u.getCategoria());
            findObj.setMarca(u.getMarca());
            _entityManager.merge(findObj);
            return findObj;
        } else {
            return null;
        }
    }

    @Override
    public Producto delete(int id) {
        var findObj = getById(id);
        if (findObj != null) {
            _entityManager.remove(findObj);
            return findObj;
        } else {
            return null;
        }
    }

    @Override
    public void changeState(int id, String estado) {
        var findObj = getById(id);
        if (findObj != null) {
            findObj.setEstado(estado);
            _entityManager.merge(findObj);
        }
    }

    @Override
    public int CantidadProductos() {
        Long count = _entityManager.createQuery(
                        "SELECT COUNT(p) FROM Producto p " +
                                "JOIN p.stockUnidades ", Long.class)
                .getSingleResult();
        return count.intValue();
    }

    @Override
    public int cantidadProductosStatus(String status) {
        Long count = _entityManager.createQuery(
                        "SELECT COUNT(p) FROM Producto p " +
                                "JOIN p.stockUnidades " +
                                "WHERE p.estado = :status", Long.class)
                .setParameter("status", status)
                .getSingleResult();
        return count.intValue();
    }

}
