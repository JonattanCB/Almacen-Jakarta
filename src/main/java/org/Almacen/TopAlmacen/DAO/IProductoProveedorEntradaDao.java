package org.Almacen.TopAlmacen.DAO;

import org.Almacen.TopAlmacen.DTO.ProductoProveedorEntrada.UpdateProductoProveedorEntradaDto;
import org.Almacen.TopAlmacen.Model.ProductoProveedorEntrada;

import java.util.List;

public interface IProductoProveedorEntradaDao {
    List<ProductoProveedorEntrada> getAll();

    ProductoProveedorEntrada getById(String id);

    ProductoProveedorEntrada create(ProductoProveedorEntrada c);

    ProductoProveedorEntrada update(UpdateProductoProveedorEntradaDto c, int id);

    void updatePrice(double price, String oc);

    void setEstado(String estado, String id);

    ProductoProveedorEntrada delete(String id);
}
