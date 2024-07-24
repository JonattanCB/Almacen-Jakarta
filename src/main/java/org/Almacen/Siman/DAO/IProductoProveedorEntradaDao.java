package org.Almacen.Siman.DAO;

import org.Almacen.Siman.DTO.ProductoProveedorEntrada.UpdateProductoProveedorEntradaDto;
import org.Almacen.Siman.Model.ProductoProveedorEntrada;
import org.Almacen.Siman.Model.Usuario;

import java.util.List;

public interface IProductoProveedorEntradaDao {
    List<ProductoProveedorEntrada> getAll();

    ProductoProveedorEntrada getById(String id);

    ProductoProveedorEntrada create(ProductoProveedorEntrada c);

    ProductoProveedorEntrada update(UpdateProductoProveedorEntradaDto c, int id);

    void updatePrice(double price, String oc);

    void setEstado(String usuario, String estado, String id);

    void setAprobadoPor(ProductoProveedorEntrada p, Usuario u);

    ProductoProveedorEntrada delete(String id);

    int cantStatus(String status);

}
