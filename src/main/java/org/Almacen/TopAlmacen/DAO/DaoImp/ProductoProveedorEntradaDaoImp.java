package org.Almacen.TopAlmacen.DAO.DaoImp;

import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.Almacen.TopAlmacen.DAO.IProductoProveedorEntradaDao;
import org.Almacen.TopAlmacen.DTO.ProductoProveedorEntrada.UpdateProductoProveedorEntradaDto;
import org.Almacen.TopAlmacen.Model.ProductoProveedorEntrada;

import java.util.List;

@Named
public class ProductoProveedorEntradaDaoImp implements IProductoProveedorEntradaDao {
    @PersistenceContext(name = "YourPU")
    private EntityManager _entityManager;

    @Override
    public List<ProductoProveedorEntrada> getAll() {
        return _entityManager.createQuery("SELECT p FROM ProductoProveedorEntrada p LEFT JOIN FETCH p.DetalleProductoProveedorEntrada LEFT JOIN FETCH p.usuario LEFT JOIN FETCH p.empresa", ProductoProveedorEntrada.class).getResultList();
    }

    @Override
    public ProductoProveedorEntrada getById(String id) {
        return _entityManager.createQuery("SELECT p FROM ProductoProveedorEntrada p LEFT JOIN FETCH  " +
                        " p.DetalleProductoProveedorEntrada LEFT JOIN FETCH p.empresa  LEFT JOIN FETCH p.empresa.tipoEmpresa " +
                        "LEFT JOIN FETCH p.usuario LEFT JOIN FETCH p.usuario.unidadDependencia WHERE p.OC = :id",
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
            findobj.setPrecioFinal(c.getPrecioFinal());
            _entityManager.merge(findobj);
            return findobj;
        }
        return null;
    }

    @Override
    public void updatePrice(double price, String oc) {
        var findObj = _entityManager.find(ProductoProveedorEntrada.class, oc);
        if (findObj != null) {
            findObj.setPrecioFinal(price);
            _entityManager.merge(findObj);
        }
    }

    @Override
    public void setEstado(String estado, String id) {
        var findObj = getById(id);
        if (findObj != null) {
            findObj.setEstado(estado);
            _entityManager.merge(findObj);
        }

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
