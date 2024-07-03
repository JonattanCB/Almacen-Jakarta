package org.Almacen.TopAlmacen.DAO;

import org.Almacen.TopAlmacen.DTO.PrecioPorTipoUnidad.UpdatePrecioPorTipoUnidadDto;
import org.Almacen.TopAlmacen.Model.PrecioPorTipoUnidad;
import org.Almacen.TopAlmacen.Model.Producto;
import org.Almacen.TopAlmacen.Model.TipoUnidad;

import java.util.List;

public interface IPrecioPorTipoUnidadDao {
    List<PrecioPorTipoUnidad> getAll();

    List<Integer> getAllbyProducto(int id);

    PrecioPorTipoUnidad getById(int id);

    PrecioPorTipoUnidad create(PrecioPorTipoUnidad c);

    PrecioPorTipoUnidad update(UpdatePrecioPorTipoUnidadDto c, int id);

    PrecioPorTipoUnidad delete(int id);

    PrecioPorTipoUnidad findIfExist(Producto p, TipoUnidad t);
}
