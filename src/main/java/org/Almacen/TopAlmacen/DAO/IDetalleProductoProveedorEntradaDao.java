package org.Almacen.TopAlmacen.DAO;

import org.Almacen.TopAlmacen.Model.DetalleProductoProveedorEntrada.UpdateCategoriaDto;
import org.Almacen.TopAlmacen.Model.DetalleProductoProveedorEntrada;

import java.util.List;

public interface IDetalleProductoProveedorEntradaDao {
    List<DetalleProductoProveedorEntrada> getAll();

    DetalleProductoProveedorEntrada getById(int id);

    void create(DetalleProductoProveedorEntrada c);

    void update(UpdateCategoriaDto c, int id);

    void delete(int id);
}
