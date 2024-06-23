package org.Almacen.TopAlmacen.DAO;

import org.Almacen.TopAlmacen.DTO.DetalleProductoProveedorEntrada.UpdateDetalleProductoProveedorEntradaDto;
import org.Almacen.TopAlmacen.Model.DetalleProductoProveedorEntrada;

import java.util.List;

public interface IDetalleProductoProveedorEntradaDao {
    List<DetalleProductoProveedorEntrada> getAll();

    DetalleProductoProveedorEntrada getById(int id);

    void create(DetalleProductoProveedorEntrada c);

    void update(UpdateDetalleProductoProveedorEntradaDto c, int id);

    void delete(int id);
}
