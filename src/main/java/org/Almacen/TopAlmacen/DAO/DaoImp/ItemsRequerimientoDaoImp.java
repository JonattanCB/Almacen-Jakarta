package org.Almacen.TopAlmacen.DAO.DaoImp;

import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.Almacen.TopAlmacen.DAO.IItemsRequerimientoDao;
import org.Almacen.TopAlmacen.DTO.ItemsRequerimiento.UpdateRequerimientoDto;
import org.Almacen.TopAlmacen.Model.ItemsRequerimiento;

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
    public ItemsRequerimiento getById(int id) {
        return _entityManager.find(ItemsRequerimiento.class, id);
    }

    @Override
    public ItemsRequerimiento create(ItemsRequerimiento c) {
        _entityManager.persist(c);
        return c;
    }

    @Override
    public ItemsRequerimiento update(UpdateRequerimientoDto c, int id) {
        var itemFound = _entityManager.find(ItemsRequerimiento.class, id);
        if (itemFound != null) {
            itemFound.setCantidad(c.getCantidad());
            itemFound.setTipoUnidad(c.getTipoUnidad());
            itemFound.setDescripcion(c.getDescripcion());
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
}
