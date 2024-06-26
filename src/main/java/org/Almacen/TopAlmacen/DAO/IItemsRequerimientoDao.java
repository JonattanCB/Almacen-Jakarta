package org.Almacen.TopAlmacen.DAO;

import org.Almacen.TopAlmacen.DTO.ItemsRequerimiento.UpdateRequerimientoDto;
import org.Almacen.TopAlmacen.Model.ItemsRequerimiento;

import java.util.List;

public interface IItemsRequerimientoDao {
    List<ItemsRequerimiento> getAll();

    ItemsRequerimiento getById(int id);

    ItemsRequerimiento create(ItemsRequerimiento c);

    ItemsRequerimiento update(UpdateRequerimientoDto c, int id);

    ItemsRequerimiento delete(int id);
}
