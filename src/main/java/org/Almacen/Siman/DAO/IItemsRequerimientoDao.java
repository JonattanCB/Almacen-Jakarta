package org.Almacen.Siman.DAO;

import org.Almacen.Siman.DTO.ItemsRequerimiento.UpdateItemsRequerimientoDto;
import org.Almacen.Siman.Model.ItemsRequerimiento;

import java.util.List;

public interface IItemsRequerimientoDao {
    List<ItemsRequerimiento> getAll();

    ItemsRequerimiento getById(int id);

    ItemsRequerimiento create(ItemsRequerimiento c);

    List<ItemsRequerimiento> getItemsByRequerimientoId(String requerimientoId);

    ItemsRequerimiento update(UpdateItemsRequerimientoDto c, int id);

    ItemsRequerimiento delete(int id);

    void deleteRequerimientoAll(String id);

}

