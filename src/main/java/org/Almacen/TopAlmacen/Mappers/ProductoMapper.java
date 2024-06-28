package org.Almacen.TopAlmacen.Mappers;

import org.Almacen.TopAlmacen.DTO.Marca.UpdateMarcaDto;
import org.Almacen.TopAlmacen.DTO.Producto.CreateProductoDto;
import org.Almacen.TopAlmacen.DTO.Producto.ProductoDto;
import org.Almacen.TopAlmacen.Model.Marca;
import org.Almacen.TopAlmacen.Model.Producto;

import java.util.List;
import java.util.stream.Collectors;

public class ProductoMapper {
    public static ProductoDto toDto(Producto producto) {
        return new ProductoDto(producto.getId(), producto.getNombre(), producto.getColor(), producto.getPeso(), CategoriaMapper.toDto(producto.getCategoria()), MarcaMapper.toDto(producto.getMarca()),producto.getFechaRegistro());
    }

    public static List<ProductoDto> toDTOList(List<Producto> productos) {
        return productos.stream()
                .map(ProductoMapper::toDto)
                .collect(Collectors.toList());
    }

    public static Producto toProductoFromCreate(CreateProductoDto dto) {
        Producto p = new Producto();
        p.setNombre(dto.getNombre());
        p.setColor(dto.getColor());
        p.setPeso(dto.getPeso());

        return p;
    }

    public static Marca toMarcaFromUpdate(UpdateMarcaDto dto) {
        Marca marca = new Marca();
        marca.setNombre(dto.getNombre());
        marca.setEstado(dto.getEstado());
        return marca;
    }
}
