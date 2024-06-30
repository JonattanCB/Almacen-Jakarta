package org.Almacen.TopAlmacen.Mappers;

import org.Almacen.TopAlmacen.DTO.Marca.UpdateMarcaDto;
import org.Almacen.TopAlmacen.DTO.Producto.CreateProductoDto;
import org.Almacen.TopAlmacen.DTO.Producto.ProductoDto;
import org.Almacen.TopAlmacen.DTO.Producto.UpdateProductoDto;
import org.Almacen.TopAlmacen.Model.Marca;
import org.Almacen.TopAlmacen.Model.Producto;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.codehaus.groovy.runtime.DefaultGroovyMethods.collect;

public class ProductoMapper {
    public static ProductoDto toDto(Producto producto) {
        return new ProductoDto(producto.getId(), producto.getNombre(), producto.getColor(), producto.getPeso(), producto.getCategoria(), producto.getMarca(), producto.getFechaRegistro());
    }

    public static Producto toProducto(ProductoDto dto) {
        return new Producto(dto.getId(), dto.getNombre(), dto.getColor(), dto.getPeso(), dto.getMarca(), dto.getCategoria(), dto.getFechaRegistro());
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
        p.setCategoria(dto.getCategoria());
        p.setMarca(dto.getMarca());
        return p;
    }

    public static Producto toProductoFromUpdate(UpdateProductoDto dto) {
        Producto p = new Producto();
        p.setNombre(dto.getNombre());
        p.setColor(dto.getColor());
        p.setPeso(dto.getPeso());
        p.setCategoria(dto.getCategoria());
        p.setMarca(dto.getMarca());
        return p;
    }

    public static String toConcatProduct(Producto p) {
        return Stream.of(p.getNombre(), p.getColor(), p.getPeso(), p.getMarca().getNombre())
                .filter(Objects::nonNull)
                .map(String::valueOf)
                .filter(s -> !s.trim().isEmpty())
                .collect(Collectors.joining(" "));
    }


}
