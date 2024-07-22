package org.Almacen.TopAlmacen.DAO;

import org.Almacen.TopAlmacen.DTO.ComprobanteSalida.UpdateComprobanteSalidaDto;
import org.Almacen.TopAlmacen.Model.ComprobanteSalida;

import java.util.List;

public interface IComprobanteSalidaDao {
    List<ComprobanteSalida> getAll();

    ComprobanteSalida getById(String id);

    ComprobanteSalida create(ComprobanteSalida c);

    ComprobanteSalida update(UpdateComprobanteSalidaDto c, String id);

    ComprobanteSalida delete(String id);

    void setEstado(String estado, String id);

}
