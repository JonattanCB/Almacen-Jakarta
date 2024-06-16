package org.Almacen.TopAlmacen.DAO.DaoImp;

import org.Almacen.TopAlmacen.DAO.IProductoDao;
import org.Almacen.TopAlmacen.Model.Producto;

import java.util.List;

public class ProductoDaoImp implements IProductoDao {
    @Override
    public List<Producto> getAll() {
        return List.of();
    }

    @Override
    public Producto getById(int id) {
        return null;
    }

    @Override
    public void create(Producto c) {

    }

    @Override
    public void update(Producto c) {

    }

    @Override
    public void delete(Producto c) {

    }
}
