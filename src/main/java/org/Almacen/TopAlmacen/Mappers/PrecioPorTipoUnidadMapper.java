package org.Almacen.TopAlmacen.Mappers;

import org.Almacen.TopAlmacen.DTO.PrecioPorTipoUnidad.CreatePrecioPorTipoUnidadDto;
import org.Almacen.TopAlmacen.DTO.PrecioPorTipoUnidad.PrecioPorTipoUnidadDto;
import org.Almacen.TopAlmacen.DTO.PrecioPorTipoUnidad.UpdatePrecioPorTipoUnidadDto;
import org.Almacen.TopAlmacen.Model.PrecioPorTipoUnidad;
import org.Almacen.TopAlmacen.Model.Producto;
import org.Almacen.TopAlmacen.Model.TipoUnidad;
import org.Almacen.TopAlmacen.Services.PrecioPorTipoUnidadService;

public class PrecioPorTipoUnidadMapper {
    public static PrecioPorTipoUnidadDto toDto(PrecioPorTipoUnidad p) {
        System.out.println(p.getStockUnidades() + "encontrado");
        return new PrecioPorTipoUnidadDto(p.getId(), p.getTipoUnidad(), p.getProducto(), p.getPrecioUnitario(), p.getUnidadesPorTipoUnidadDeProducto(), p.getStockUnidades());
    }

    public static PrecioPorTipoUnidad toPrecioPorTipoUnidadFromCreate(CreatePrecioPorTipoUnidadDto dto) {
        var p = new PrecioPorTipoUnidad();
        p.setTipoUnidad(dto.getTipoUnidad());
        p.setProducto(dto.getProducto());
        p.setPrecioUnitario(dto.getPrecio());
        p.setUnidadesPorTipoUnidadDeProducto(dto.getUnidadesPorTipoUnidadPorProducto());
        p.setStockUnidades(dto.getStockUnidades());
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
