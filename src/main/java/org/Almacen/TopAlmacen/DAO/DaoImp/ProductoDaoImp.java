package org.Almacen.TopAlmacen.DAO.DaoImp;

import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.Almacen.TopAlmacen.DAO.IProductoDao;
import org.Almacen.TopAlmacen.DTO.Producto.UpdateProductoDto;
import org.Almacen.TopAlmacen.Model.Producto;

import java.util.List;

@Named
public class ProductoDaoImp implements IProductoDao {
    @PersistenceContext(name = "YourPU")
    private EntityManager _entityManager;

    @Override
    public List<Producto> getAll() {
        return _entityManager.createQuery("SELECT p FROM Producto p", Producto.class).getResultList();
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
}
