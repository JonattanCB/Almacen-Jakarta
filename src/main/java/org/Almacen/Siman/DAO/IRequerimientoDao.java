package org.Almacen.Siman.DAO;

import org.Almacen.Siman.DTO.Requerimiento.UpdateRequerimientoDto;
import org.Almacen.Siman.Model.Requerimiento;
import org.Almacen.Siman.Model.Usuario;

import java.util.List;

public interface IRequerimientoDao {
    List<Requerimiento> getAll(int idDependencia);

    List<Requerimiento> getAllbyStatusPendiente(int idDependencia);

    List<Requerimiento> getAllByIdDependenciaUser(int idunidad, int idUser);

    int cantidadRequerimientosUserUnidad(int idunidad, int idUser);

    int cantidadRequerimientosDependencia(int idDependencia);

    int cantidadRequerimientosUserUnidadStatus(int idunidad, int idUser, String status);

    int cantidadRequerimientosdependenciaStatus(int iddependencia, String status);

    Requerimiento setAprobado(String idReq, Usuario usuario);

    List<Requerimiento> getAllFinalized();

    List<Requerimiento> getRequerimientoByDependencia();

    List<Requerimiento> getAllAprobed();

    Requerimiento getById(String id);

    Requerimiento create(Requerimiento c);

    Requerimiento update(UpdateRequerimientoDto c, String id);

    Requerimiento delete(String id);

    void setEstado(String id, String estado, String observacion);
}
