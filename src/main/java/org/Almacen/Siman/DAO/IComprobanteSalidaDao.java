package org.Almacen.Siman.DAO;

import org.Almacen.Siman.DTO.ComprobanteSalida.UpdateComprobanteSalidaDto;
import org.Almacen.Siman.Model.ComprobanteSalida;
import org.Almacen.Siman.Model.Usuario;

import java.util.List;

public interface IComprobanteSalidaDao {
    List<ComprobanteSalida> getAll();

    ComprobanteSalida getById(String id);

    ComprobanteSalida create(ComprobanteSalida c);

    ComprobanteSalida update(UpdateComprobanteSalidaDto c, String id);

    ComprobanteSalida setAprobado(ComprobanteSalida cs, Usuario usuario);

    ComprobanteSalida delete(String id);

    void setEstado(String estado, String id);

    int cantStatus(String status);

}
