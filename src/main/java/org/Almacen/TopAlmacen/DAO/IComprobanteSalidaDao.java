package org.Almacen.TopAlmacen.DAO;

import org.Almacen.TopAlmacen.DTO.ComprobanteSalida.UpdateComprobanteSalidaDto;
import org.Almacen.TopAlmacen.Model.ComprobanteSalida;

import java.util.List;

public interface IComprobanteSalidaDao {
    List<ComprobanteSalida> getAll();

    ComprobanteSalida getById(int id);

    ComprobanteSalida create(ComprobanteSalida c);

    ComprobanteSalida update(UpdateComprobanteSalidaDto c, int id);

    ComprobanteSalida delete(int id);

    void setEstado(String estado, int id);

}
