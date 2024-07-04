package org.Almacen.TopAlmacen.DAO;

import org.Almacen.TopAlmacen.DTO.ProductoProveedorEntrada.UpdateProductoProveedorEntradaDto;
import org.Almacen.TopAlmacen.Model.ProductoProveedorEntrada;

import java.util.List;

public interface IProductoProveedorEntradaDao {
    List<ProductoProveedorEntrada> getAll();

    ProductoProveedorEntrada getById(int id);

    ProductoProveedorEntrada create(ProductoProveedorEntrada c);

    ProductoProveedorEntrada update(UpdateProductoProveedorEntradaDto c, int id);

    ProductoProveedorEntrada updatePrice(double price, String oc);

    ProductoProveedorEntrada delete(int id);
}
