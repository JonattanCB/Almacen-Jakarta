package org.Almacen.TopAlmacen.DAO.DaoImp;

import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import org.Almacen.TopAlmacen.DAO.IPrecioPorTipoUnidadDao;
import org.Almacen.TopAlmacen.DTO.PrecioPorTipoUnidad.PrecioPorTipoUnidadDto;
import org.Almacen.TopAlmacen.DTO.PrecioPorTipoUnidad.UpdatePrecioPorTipoUnidadDto;
import org.Almacen.TopAlmacen.Model.PrecioPorTipoUnidad;
import org.Almacen.TopAlmacen.Model.Producto;
import org.Almacen.TopAlmacen.Model.TipoUnidad;

import java.util.List;

@Named
public class PrecioPorTipoUnidadDaoImp implements IPrecioPorTipoUnidadDao {
    @PersistenceContext(name = "YourPU")
    private EntityManager _entityManager;

    @Override
    public List<PrecioPorTipoUnidad> getAll() {
        return _entityManager.createQuery("SELECT p FROM PrecioPorTipoUnidad p", PrecioPorTipoUnidad.class).getResultList();
    }

    @Override
    public PrecioPorTipoUnidad getById(int id) {
        return _entityManager.find(PrecioPorTipoUnidad.class, id);
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
    public PrecioPorTipoUnidad delete(int id) {
        var findObj = _entityManager.find(PrecioPorTipoUnidad.class, id);
        if (findObj != null) {
            _entityManager.remove(findObj);
            return findObj;
        } else {
            return null;
        }
    }

    @Override
    public PrecioPorTipoUnidad findIfExist(Producto p, TipoUnidad t) {
        try {
            return _entityManager.createQuery("SELECT p FROM PrecioPorTipoUnidad p WHERE p.producto = :producto AND p.tipoUnidad.Abrev = :abrev", PrecioPorTipoUnidad.class)
                    .setParameter("producto", p)
                    .setParameter("abrev", t)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
