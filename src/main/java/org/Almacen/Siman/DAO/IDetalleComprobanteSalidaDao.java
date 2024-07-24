package org.Almacen.Siman.DAO;


import org.Almacen.Siman.Model.DetalleComprobanteSalida;

import java.util.List;

public interface IDetalleComprobanteSalidaDao {

    List<DetalleComprobanteSalida> getAll();

    List<DetalleComprobanteSalida> getAllbyComprobateSalida(String id);

    DetalleComprobanteSalida getById(int id);

    DetalleComprobanteSalida create(DetalleComprobanteSalida c);

    void delete(DetalleComprobanteSalida c);
}
