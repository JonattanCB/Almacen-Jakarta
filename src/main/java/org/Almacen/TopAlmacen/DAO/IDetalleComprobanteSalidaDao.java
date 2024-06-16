package org.Almacen.TopAlmacen.DAO;


import org.Almacen.TopAlmacen.Model.DetalleComprobanteSalida;

import java.util.List;

public interface IDetalleComprobanteSalidaDao {
    List<DetalleComprobanteSalida> getAll();

    DetalleComprobanteSalida getById(int id);

    void create(DetalleComprobanteSalida c);

    void update(DetalleComprobanteSalida c);

    void delete(DetalleComprobanteSalida c);
}
