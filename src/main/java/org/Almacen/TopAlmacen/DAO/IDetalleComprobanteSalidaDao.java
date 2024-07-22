package org.Almacen.TopAlmacen.DAO;


import org.Almacen.TopAlmacen.Model.DetalleComprobanteSalida;

import java.util.List;

public interface IDetalleComprobanteSalidaDao {

    List<DetalleComprobanteSalida> getAll();

    List<DetalleComprobanteSalida> getAllbyComprobateSalida(String id);

    DetalleComprobanteSalida getById(int id);

    DetalleComprobanteSalida create(DetalleComprobanteSalida c);
}
