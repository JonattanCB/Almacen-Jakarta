package org.Almacen.Siman.DAO.DaoImp;

import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.Almacen.Siman.DAO.IProductoProveedorEntradaDao;
import org.Almacen.Siman.DTO.ProductoProveedorEntrada.UpdateProductoProveedorEntradaDto;
import org.Almacen.Siman.Mappers.UsuarioMapper;
import org.Almacen.Siman.Model.ProductoProveedorEntrada;
import org.Almacen.Siman.Model.Usuario;

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
    public void setAprobadoPor(ProductoProveedorEntrada p, Usuario u) {
        p.setAprobadoPor(UsuarioMapper.toConcatuser(u));
        _entityManager.merge(p);
    }

    @Override
    public void setEstado(String usuario, String estado, String id) {
        var findObj = getById(id);
        if (findObj != null) {
            findObj.setEstado(estado);
            findObj.setAprobadoPor(usuario);
            _entityManager.merge(findObj);
        }

    }

    @Override
    public ProductoProveedorEntrada delete(String id) {
        var findobj = _entityManager.find(ProductoProveedorEntrada.class, id);
        if (findobj != null) {
            _entityManager.remove(findobj);
        }
        return null;
    }

    @Override
    public int cantStatus(String status) {
        Long count = _entityManager.createQuery(
                        "SELECT COUNT(p) FROM ProductoProveedorEntrada p " +
                                "LEFT JOIN p.DetalleProductoProveedorEntrada " +
                                "LEFT JOIN p.usuario " +
                                "LEFT JOIN p.empresa where  p.estado= :status", Long.class)
                .setParameter("status", status)
                .getSingleResult();
        return count.intValue();
    }
}
