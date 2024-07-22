package org.Almacen.TopAlmacen.Mappers;


import org.Almacen.TopAlmacen.DTO.HistorialPrecios.HistorialPreciosDto;
import org.Almacen.TopAlmacen.Model.HistorialPrecios;

public class HistorialPreciosMapper {
    public static HistorialPreciosDto toDto(HistorialPrecios h) {
        return new HistorialPreciosDto(h.getId(), ProductoMapper.toConcatProduct(h.getPrecioPorTipoUnidad().getProducto()), h.getPrecioRegistro(), h.getFechaRegistro(), h.getResponsable());
    }
}
