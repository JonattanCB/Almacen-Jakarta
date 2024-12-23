package org.Almacen.Siman.DAO.DaoImp;

import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.Almacen.Siman.DAO.IItemsRequerimientoDao;
import org.Almacen.Siman.DTO.ItemsRequerimiento.UpdateItemsRequerimientoDto;
import org.Almacen.Siman.Model.ItemsRequerimiento;

import java.util.List;

@Named
public class ItemsRequerimientoDaoImp implements IItemsRequerimientoDao {
    @PersistenceContext(name = "YourPU")
    private EntityManager _entityManager;

    @Override
    public List<ItemsRequerimiento> getAll() {
        return _entityManager.createQuery("SELECT i FROM ItemsRequerimiento i JOIN FETCH i.producto JOIN FETCH i.tipoUnidad", ItemsRequerimiento.class).getResultList();
    }

    @Override
    public List<ItemsRequerimiento> getItemsByRequerimientoId(String requerimientoId) {
        return _entityManager.createQuery(
                        "SELECT i FROM ItemsRequerimiento i JOIN FETCH i.producto JOIN FETCH i.tipoUnidad JOIN FETCH i.requerimiento WHERE i.requerimiento.id = :requerimientoId",
                        ItemsRequerimiento.class
                )
                .setParameter("requerimientoId", requerimientoId)
                .getResultList();
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
        } else {
            return null;
        }
    }

    @Override
    public ItemsRequerimiento delete(int id) {
        var itemFound = _entityManager.find(ItemsRequerimiento.class, id);
        if (itemFound != null) {
            _entityManager.remove(itemFound);
            return itemFound;
        } else {
            return null;
        }
    }

    @Override
    public void deleteRequerimientoAll(String id) {
        System.out.println(id);
        var query = _entityManager.createQuery(
                "delete from ItemsRequerimiento i where i.requerimiento.id = :id "
        );
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
