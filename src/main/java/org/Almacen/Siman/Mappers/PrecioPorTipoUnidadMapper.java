package org.Almacen.Siman.Mappers;

import org.Almacen.Siman.DTO.PrecioPorTipoUnidad.CreatePrecioPorTipoUnidadDto;
import org.Almacen.Siman.DTO.PrecioPorTipoUnidad.PrecioPorTipoUnidadDto;
import org.Almacen.Siman.DTO.PrecioPorTipoUnidad.UpdatePrecioPorTipoUnidadDto;
import org.Almacen.Siman.Model.PrecioPorTipoUnidad;

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

    public static PrecioPorTipoUnidad toPrecioPorTipoUnidadFromUpdate(UpdatePrecioPorTipoUnidadDto dto) {
        PrecioPorTipoUnidad p = new PrecioPorTipoUnidad();
        p.setTipoUnidad(dto.getTipoUnidad());
        p.setProducto(dto.getProducto());
        p.setPrecioUnitario(dto.getPrecio());
        p.setUnidadesPorTipoUnidadDeProducto(dto.getUnidadesPorTipoUnidadPorProducto());
        return p;

    }

    public static PrecioPorTipoUnidad toEntity(PrecioPorTipoUnidadDto p) {
        var precio = new PrecioPorTipoUnidad();
        precio.setId(p.getId());
        precio.setTipoUnidad(p.getTipoUnidad());
        precio.setProducto(p.getProducto());
        precio.setPrecioUnitario(p.getPrecioUnitario());
        precio.setUnidadesPorTipoUnidadDeProducto(p.getUnidadesPorTipoUnidadPorProducto());

        return precio;
    }

}