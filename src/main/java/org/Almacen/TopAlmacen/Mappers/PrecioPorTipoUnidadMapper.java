package org.Almacen.TopAlmacen.Mappers;

import org.Almacen.TopAlmacen.DTO.PrecioPorTipoUnidad.CreatePrecioPorTipoUnidadDto;
import org.Almacen.TopAlmacen.DTO.PrecioPorTipoUnidad.PrecioPorTipoUnidadDto;
import org.Almacen.TopAlmacen.Model.PrecioPorTipoUnidad;

public class PrecioPorTipoUnidadMapper {
    public static PrecioPorTipoUnidadDto toDto(PrecioPorTipoUnidad p) {
        return new PrecioPorTipoUnidadDto(p.getId(), p.getTipoUnidad(), p.getProducto(), p.getPrecioUnitario(), p.getUnidadesPorTipoUnidadDeProducto());
    }

    public static PrecioPorTipoUnidad toPrecioPorTipoUnidadFromCreate(CreatePrecioPorTipoUnidadDto dto) {
        PrecioPorTipoUnidad p = new PrecioPorTipoUnidad();
        p.setTipoUnidad(dto.getTipoUnidad());
        p.setProducto(dto.getProducto());
        p.setPrecioUnitario(dto.getPrecio());
        p.setUnidadesPorTipoUnidadDeProducto(dto.getUnidadesPorTipoUnidadPorProducto());
        return p;

    }
}
