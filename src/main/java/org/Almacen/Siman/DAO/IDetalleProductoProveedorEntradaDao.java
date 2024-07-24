package org.Almacen.Siman.DAO;

import org.Almacen.Siman.DTO.DetalleProductoProveedorEntrada.UpdateDetalleProductoProveedorEntradaDto;
import org.Almacen.Siman.Model.DetalleProductoProveedorEntrada;

import java.util.List;

public interface IDetalleProductoProveedorEntradaDao {
    List<DetalleProductoProveedorEntrada> getAll();

    List<DetalleProductoProveedorEntrada> getAllByProveedorEntrada(String id);

    DetalleProductoProveedorEntrada getById(int id);

    DetalleProductoProveedorEntrada create(DetalleProductoProveedorEntrada c);

    DetalleProductoProveedorEntrada update(UpdateDetalleProductoProveedorEntradaDto c, int id);

    DetalleProductoProveedorEntrada delete(int id);
}
