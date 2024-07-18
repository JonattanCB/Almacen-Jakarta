package org.Almacen.TopAlmacen.DAO;

import org.Almacen.TopAlmacen.DTO.Requerimiento.UpdateRequerimientoDto;
import org.Almacen.TopAlmacen.Model.Requerimiento;

import java.util.List;

public interface IRequerimientoDao {
    List<Requerimiento> getAll();

    List<Requerimiento> getAllFinalized();

    List<Requerimiento> getRequerimientoByDependencia(int id);

    List<Requerimiento> getAllAprobed();

    Requerimiento getById(String id);

    Requerimiento create(Requerimiento c);

    Requerimiento update(UpdateRequerimientoDto c, String id);

    Requerimiento delete(String id);

    void setEstado(String id, String estado, String observacion);
}
