package org.Almacen.Siman.DAO;

import org.Almacen.Siman.DTO.PrecioPorTipoUnidad.UpdatePrecioPorTipoUnidadDto;
import org.Almacen.Siman.Model.PrecioPorTipoUnidad;
import org.Almacen.Siman.Model.Producto;
import org.Almacen.Siman.Model.TipoUnidad;

import java.util.List;

public interface IPrecioPorTipoUnidadDao {
    List<PrecioPorTipoUnidad> getAll();

    List<Integer> getAllbyProducto(int id);

    PrecioPorTipoUnidad getPptuByTipoUnidadProducto(int productoId, int tipoUnidadId);

    List<PrecioPorTipoUnidad> getAllTipoUnidadbyProducto(int id);

    PrecioPorTipoUnidad getById(int id);

    PrecioPorTipoUnidad create(PrecioPorTipoUnidad c);

    PrecioPorTipoUnidad update(UpdatePrecioPorTipoUnidadDto c, int id);

    PrecioPorTipoUnidad delete(int id);

    PrecioPorTipoUnidad findIfExist(Producto p, TipoUnidad t);

    PrecioPorTipoUnidad getByIdProductoIdTipoUnidad(int idProducto, int idTipoUnidad);

    void updatePrecioU(double p, int id);

    PrecioPorTipoUnidad getByIdProducto(int idProducto);

}
