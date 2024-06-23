package org.Almacen.TopAlmacen.DAO;

import org.Almacen.TopAlmacen.DTO.ItemsRequerimiento.UpdateRequerimientoDto;
import org.Almacen.TopAlmacen.Model.Requerimiento;

import java.util.List;

public interface IRequerimientoDao {
    List<Requerimiento> getAll();

    Requerimiento getById(int id);

    void create(Requerimiento c);

    void update(UpdateRequerimientoDto c, int id);

    void delete(int id);
}
