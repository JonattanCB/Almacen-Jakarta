package org.Almacen.TopAlmacen.DAO.DaoImp;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.Almacen.TopAlmacen.DAO.IProductoProveedorEntradaDao;
import org.Almacen.TopAlmacen.DTO.ProductoProveedorEntrada.UpdateProductoProveedorEntradaDto;
import org.Almacen.TopAlmacen.Model.ProductoProveedorEntrada;

import java.util.List;

public class ProductoProveedorEntradaDaoImp implements IProductoProveedorEntradaDao {
    @PersistenceContext(name = "YourPU")
    private EntityManager _entityManager;

    @Override
    public List<ProductoProveedorEntrada> getAll() {
        return _entityManager.createQuery("SELECT p FROM ProductoProveedorEntrada p", ProductoProveedorEntrada.class).getResultList();
    }

    @Override
    public ProductoProveedorEntrada getById(int id) {
        return _entityManager.createQuery("SELECT p FROM ProductoProveedorEntrada p LEFT JOIN FETCH p.DetalleProductoProveedorEntrada WHERE p.OC = :id",
                ProductoProveedorEntrada.class).setParameter("id", id).getSingleResult();
    }

    @Override
    public ProductoProveedorEntrada create(ProductoProveedorEntrada c) {
        _entityManager.persist(c);
        return c;
    }

    @Override
    public ProductoProveedorEntrada update(UpdateProductoProveedorEntradaDto c, int id) {
        var findobj = _entityManager.find(ProductoProveedorEntrada.class, id);
        if (findobj != null) {
            findobj.setEmpresa(c.getEmpresa());
            findobj.setUsuario(c.getUsuario());
            findobj.setPrecioFinal(c.getPrecioFinal());
            _entityManager.merge(findobj);
            return findobj;
        }
        return null;
    }

    @Override
    public ProductoProveedorEntrada delete(int id) {
        var findobj = _entityManager.find(ProductoProveedorEntrada.class, id);
        if (findobj != null) {
            _entityManager.remove(findobj);
        }
        return null;
    }
}
