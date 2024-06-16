package org.Almacen.TopAlmacen.DAO;

import org.Almacen.TopAlmacen.Model.ComprobanteSalida;

import java.util.List;

public interface IComprobanteSalidaDao {
    List<ComprobanteSalida> getAll();

    ComprobanteSalida getById(int id);

    void create(ComprobanteSalida c);

    void update(ComprobanteSalida c);

    void delete(ComprobanteSalida c);
}
