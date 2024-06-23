package org.Almacen.TopAlmacen.DAO;

import org.Almacen.TopAlmacen.Model.ItemsRequerimiento.UpdateCategoriaDto;
import org.Almacen.TopAlmacen.Model.ItemsRequerimiento;

import java.util.List;

public interface IItemsRequerimientoDao {
    List<ItemsRequerimiento> getAll();

    ItemsRequerimiento getById(int id);

    void create(ItemsRequerimiento c);

    void update(UpdateCategoriaDto c, int id);

    void delete(int id);
}
