package org.Almacen.TopAlmacen.DAO;


import org.Almacen.TopAlmacen.Model.DetalleComprobanteSalida;

import java.util.List;

public interface IDetalleComprobanteSalidaDao {

    List<DetalleComprobanteSalida> getAll();

    DetalleComprobanteSalida getById(int id);

    DetalleComprobanteSalida create(DetalleComprobanteSalida c);
}
