package org.Almacen.Siman.DAO.DaoImp;

import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import org.Almacen.Siman.DAO.IPrecioPorTipoUnidadDao;
import org.Almacen.Siman.DTO.PrecioPorTipoUnidad.UpdatePrecioPorTipoUnidadDto;
import org.Almacen.Siman.Model.PrecioPorTipoUnidad;
import org.Almacen.Siman.Model.Producto;
import org.Almacen.Siman.Model.TipoUnidad;

import java.util.List;

@Named
public class PrecioPorTipoUnidadDaoImp implements IPrecioPorTipoUnidadDao {
    @PersistenceContext(name = "YourPU")
    private EntityManager _entityManager;

    @Override
    public List<PrecioPorTipoUnidad> getAll() {
        return _entityManager.createQuery("SELECT p FROM PrecioPorTipoUnidad p JOIN FETCH p.tipoUnidad JOIN FETCH p.producto", PrecioPorTipoUnidad.class).getResultList();
    }

    @Override
    public List<Integer> getAllbyProducto(int id) {
        var query = _entityManager.createQuery(
                "SELECT p.tipoUnidad.id FROM PrecioPorTipoUnidad p WHERE p.producto.id = :id", Integer.class);
        query.setParameter("id", id);
        return query.getResultList();
    }

    @Override
    public PrecioPorTipoUnidad getPptuByTipoUnidadProducto(int productoId, int tipoUnidadId) {
        var query = _entityManager.createQuery("SELECT p FROM PrecioPorTipoUnidad p JOIN FETCH p.tipoUnidad JOIN FETCH p.producto WHERE p.producto.id= :id AND p.tipoUnidad.id= :id", PrecioPorTipoUnidad.class);
        query.setParameter("id", productoId);
        query.setParameter("id", tipoUnidadId);
        return query.getSingleResult();
    }


    @Override
    public List<PrecioPorTipoUnidad> getAllTipoUnidadbyProducto(int id) {
        var query = _entityManager.createQuery(
                "SELECT p.tipoUnidad FROM PrecioPorTipoUnidad p WHERE p.producto.id = :id", PrecioPorTipoUnidad.class);
        query.setParameter("id", id);
        return query.getResultList();
    }

    @Override
    public PrecioPorTipoUnidad getById(int id) {
        var query = _entityManager.createQuery(
                "SELECT p FROM PrecioPorTipoUnidad p JOIN FETCH p.tipoUnidad JOIN FETCH p.producto WHERE p.id = :id", PrecioPorTipoUnidad.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public PrecioPorTipoUnidad create(PrecioPorTipoUnidad c) {
        _entityManager.persist(c);
        return c;
    }

    @Override
    public PrecioPorTipoUnidad update(UpdatePrecioPorTipoUnidadDto c, int id) {
        var findObj = _entityManager.find(PrecioPorTipoUnidad.class, id);
        if (findObj != null) {
            findObj.setTipoUnidad(c.getTipoUnidad());
            findObj.setProducto(c.getProducto());
            findObj.setPrecioUnitario(c.getPrecio());
            findObj.setUnidadesPorTipoUnidadDeProducto(c.getUnidadesPorTipoUnidadPorProducto());
            _entityManager.merge(findObj);
            return findObj;
        } else {
            return null;
        }
    }

    @Override
    public void updatePrecioU(double newPre, int id) {
        var findObj = _entityManager.find(PrecioPorTipoUnidad.class, id);
        findObj.setPrecioUnitario(newPre);
        _entityManager.merge(findObj);
    }

    @Override
    public PrecioPorTipoUnidad delete(int id) {
        PrecioPorTipoUnidad entity = _entityManager.find(PrecioPorTipoUnidad.class, id);
        if (entity != null) {
            _entityManager.remove(entity);
            return entity;
        }
        return null;
    }


    @Override
    public PrecioPorTipoUnidad findIfExist(Producto producto, TipoUnidad tipoUnidad) {
        try {
            return _entityManager.createQuery(
                            "SELECT p FROM PrecioPorTipoUnidad  p  JOIN FETCH p.producto JOIN FETCH p.tipoUnidad WHERE p.producto.id = :producto AND p.tipoUnidad.id = :tipoUnidad",
                            PrecioPorTipoUnidad.class)
                    .setParameter("producto", producto.getId())
                    .setParameter("tipoUnidad", tipoUnidad.getId())
                    .getSingleResult();
        } catch (NoResultException e) {

            return null;
        }
    }

    @Override
    public PrecioPorTipoUnidad getByIdProductoIdTipoUnidad(int idProducto, int idTipoUnidad) {
        try {
            return _entityManager.createQuery(
                            "SELECT p FROM PrecioPorTipoUnidad p JOIN FETCH p.tipoUnidad JOIN FETCH  p.producto  WHERE p.producto.id = :producto AND p.tipoUnidad.id = :tipoUnidad",
                            PrecioPorTipoUnidad.class)
                    .setParameter("producto", idProducto)
                    .setParameter("tipoUnidad", idTipoUnidad)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }


    @Override
    public PrecioPorTipoUnidad getByIdProducto(int idProducto) {
        try {
            return _entityManager.createQuery(
                            "SELECT p FROM PrecioPorTipoUnidad p JOIN FETCH p.tipoUnidad JOIN FETCH p.producto JOIN FETCH p.producto.categoria " +
                                    "JOIN FETCH p.producto.marca WHERE p.producto.id = :producto AND p.tipoUnidad.Abrev = 'UND'",
                            PrecioPorTipoUnidad.class)
                    .setParameter("producto", idProducto)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
