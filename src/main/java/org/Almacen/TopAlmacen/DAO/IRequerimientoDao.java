package org.Almacen.TopAlmacen.DAO;

import org.Almacen.TopAlmacen.DTO.Requerimiento.UpdateRequerimientoDto;
import org.Almacen.TopAlmacen.Model.Requerimiento;

import java.util.List;

public interface IRequerimientoDao {
    List<Requerimiento> getAll();

    Requerimiento getById(int id);

    Requerimiento create(Requerimiento c);

    Requerimiento update(UpdateRequerimientoDto c, int id);

    Requerimiento delete(int id);
}
