package org.Almacen.Siman.DAO;


import org.Almacen.Siman.DTO.Producto.UpdateProductoDto;
import org.Almacen.Siman.Model.Producto;

import java.util.List;

public interface IProductoDao {
    List<Producto> getAll();

    List<Producto> getAllFalseEstado();

    List<Producto> getAllbyProductos();

    Producto getById(int id);

    Producto create(Producto c);

    Producto update(UpdateProductoDto u, int id);

    Producto delete(int id);

    void changeState(int id, String estado);

    int CantidadProductos();

    int cantidadProductosStatus(String status);

}
