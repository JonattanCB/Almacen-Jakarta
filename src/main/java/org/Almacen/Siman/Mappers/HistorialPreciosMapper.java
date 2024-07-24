package org.Almacen.Siman.Mappers;


import org.Almacen.Siman.DTO.HistorialPrecios.HistorialPreciosDto;
import org.Almacen.Siman.Model.HistorialPrecios;

public class HistorialPreciosMapper {
    public static HistorialPreciosDto toDto(HistorialPrecios h) {
        return new HistorialPreciosDto(h.getId(), ProductoMapper.toConcatProduct(h.getPrecioPorTipoUnidad().getProducto()), h.getPrecioRegistro(), h.getFechaRegistro(), h.getResponsable());
    }
}
