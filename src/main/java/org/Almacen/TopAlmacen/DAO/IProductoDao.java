package org.Almacen.TopAlmacen.DAO;


import org.Almacen.TopAlmacen.DTO.Producto.UpdateProductoDto;
import org.Almacen.TopAlmacen.Model.Producto;

import java.util.List;

public interface IProductoDao {
    List<Producto> getAll();

    List<Producto> getAllFalseEstado();

    Producto getById(int id);

    Producto create(Producto c);

    Producto update(UpdateProductoDto u, int id);

    Producto delete(int id);

    void changeState(int id, String estado);

}
