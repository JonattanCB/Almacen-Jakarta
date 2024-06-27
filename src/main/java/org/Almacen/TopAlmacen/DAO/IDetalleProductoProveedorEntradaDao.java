package org.Almacen.TopAlmacen.DAO;

import org.Almacen.TopAlmacen.DTO.DetalleProductoProveedorEntrada.UpdateDetalleProductoProveedorEntradaDto;
import org.Almacen.TopAlmacen.Model.DetalleComprobanteSalida;
import org.Almacen.TopAlmacen.Model.DetalleProductoProveedorEntrada;

import java.util.List;

public interface IDetalleProductoProveedorEntradaDao {
    List<DetalleProductoProveedorEntrada> getAll();

    List<DetalleProductoProveedorEntrada> getAllByProveedorEntrada(int id);

    DetalleProductoProveedorEntrada getById(int id);

    DetalleProductoProveedorEntrada create(DetalleProductoProveedorEntrada c);

    DetalleProductoProveedorEntrada update(UpdateDetalleProductoProveedorEntradaDto c, int id);

    DetalleProductoProveedorEntrada delete(int id);
}
