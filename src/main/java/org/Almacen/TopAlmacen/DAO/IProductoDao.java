package org.Almacen.TopAlmacen.DAO;


import org.Almacen.TopAlmacen.Model.Producto;

import java.util.List;

public interface IProductoDao {
    List<Producto> getAll();

    Producto getById(int id);

    void create(Producto c);

    void update(Producto c);

    void delete(Producto c);
}
