package org.Almacen.TopAlmacen.Mappers;

import org.Almacen.TopAlmacen.DTO.Categoria.CategoriaDto;
import org.Almacen.TopAlmacen.DTO.Categoria.CreateCategoriaDto;
import org.Almacen.TopAlmacen.DTO.Categoria.UpdateCategoriaDto;
import org.Almacen.TopAlmacen.Model.Categoria;

import java.util.List;
import java.util.stream.Collectors;

public class CategoriaMapper {

    public static CategoriaDto toDto(Categoria categoria) {
        return new CategoriaDto(categoria.getId(), categoria.getNombre(), categoria.getDescripcion(), categoria.getEstado(), categoria.getFechaRegistro());
    }

    public static Categoria toCategoria(CategoriaDto dto) {
        return new Categoria(dto.getId(),dto.getNombre(),dto.getDescripcion(),dto.getEstado(), dto.getFechaRegistro(), null);
    }

    public static Categoria toCategoriaFromCreate(CreateCategoriaDto dto) {
        Categoria categoria = new Categoria();
        categoria.setNombre(dto.getNombre());
        categoria.setDescripcion(dto.getDescripcion());
        categoria.setEstado(dto.getEstado());
        return categoria;
    }

    public static Categoria toCategoriaFromUpdate(UpdateCategoriaDto dto) {
        Categoria categoria = new Categoria();
        categoria.setNombre(dto.getNombre());
        categoria.setDescripcion(dto.getDescripcion());
        categoria.setEstado(dto.getEstado());
        return categoria;
    }


}
