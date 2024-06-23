package org.Almacen.TopAlmacen.DAO;

import org.Almacen.TopAlmacen.DTO.ProductoProveedorEntrada.UpdateProductoProveedorEntradaDto;
import org.Almacen.TopAlmacen.Model.ProductoProveedorEntrada;

import java.util.List;

public interface IProductoProveedorEntradaDao {
    List<ProductoProveedorEntrada> getAll();

    ProductoProveedorEntrada getById(int id);

    void create(ProductoProveedorEntrada c);

    void update(UpdateProductoProveedorEntradaDto c, int id);

    void delete(int id);
}
