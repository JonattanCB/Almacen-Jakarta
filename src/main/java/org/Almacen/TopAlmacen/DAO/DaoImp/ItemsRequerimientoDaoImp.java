package org.Almacen.TopAlmacen.DAO.DaoImp;

import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.Almacen.TopAlmacen.DAO.IItemsRequerimientoDao;
import org.Almacen.TopAlmacen.DTO.ItemsRequerimiento.ItemsRequerimientoDto;
import org.Almacen.TopAlmacen.DTO.ItemsRequerimiento.UpdateItemsRequerimientoDto;
import org.Almacen.TopAlmacen.Model.DetalleProductoProveedorEntrada;
import org.Almacen.TopAlmacen.Model.ItemsRequerimiento;
import org.Almacen.TopAlmacen.Model.Requerimiento;

import java.util.List;

@Named
public class ItemsRequerimientoDaoImp implements IItemsRequerimientoDao {
    @PersistenceContext(name = "YourPU")
    private EntityManager _entityManager;

    @Override
    public List<ItemsRequerimiento> getAll() {
        return _entityManager.createQuery("SELECT i FROM ItemsRequerimiento i", ItemsRequerimiento.class).getResultList();
    }

    @Override
    public List<ItemsRequerimiento> getAllByRequerimiento(int id) {
        var query = _entityManager.createQuery(
                "SELECT i FROM ItemsRequerimiento i  LEFT JOIN FETCH  i.tipoUnidad  LEFT JOIN FETCH i.requerimiento  LEFT JOIN FETCH  i.requerimiento.unidadDependencia  where i.requerimiento.id = :id ",
                ItemsRequerimiento.class);
        query.setParameter("id", id);
        return query.getResultList();
    }

    @Override
    public ItemsRequerimiento getById(int id) {
        return _entityManager.find(ItemsRequerimiento.class, id);
    }

    @Override
    public ItemsRequerimiento create(ItemsRequerimiento c) {
        _entityManager.persist(c);
        return c;
    }

    @Override
    public ItemsRequerimiento update(UpdateItemsRequerimientoDto c, int id) {
        var itemFound = _entityManager.find(ItemsRequerimiento.class, id);
        if (itemFound != null) {
            itemFound.setCantidad(c.getCantidad());
            itemFound.setTipoUnidad(c.getTipoUnidad());
            itemFound.setProducto(c.getProducto());
            return _entityManager.merge(itemFound);
        }else{
            return null;
        }
    }

    @Override
    public ItemsRequerimiento delete(int id) {
        var itemFound = _entityManager.find(ItemsRequerimiento.class, id);
        if (itemFound != null) {
            _entityManager.remove(itemFound);
            return itemFound;
        }else {
            return null;
        }
    }
    /*
    DELETE FROM public.producto
	WHERE <condition>;
     */

    @Override
    public void deleteRequerimientoAll(int id) {
        var query = _entityManager.createQuery(
                "delete from ItemsRequerimiento i where i.requerimiento.id = :id "
        );
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
