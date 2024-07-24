package org.Almacen.Siman.DAO;

import org.Almacen.Siman.DTO.Requerimiento.UpdateRequerimientoDto;
import org.Almacen.Siman.Model.Requerimiento;

import java.util.List;

public interface IRequerimientoDao {
    List<Requerimiento> getAll(int idDependencia);

    List<Requerimiento> getAllFinalized();

    List<Requerimiento> getRequerimientoByDependencia();

    List<Requerimiento> getAllAprobed();

    Requerimiento getById(String id);

    Requerimiento create(Requerimiento c);

    Requerimiento update(UpdateRequerimientoDto c, String id);

    Requerimiento delete(String id);

    void setEstado(String id, String estado, String observacion);
}
