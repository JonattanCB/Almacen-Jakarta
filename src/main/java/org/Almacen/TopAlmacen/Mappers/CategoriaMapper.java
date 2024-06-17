package org.Almacen.TopAlmacen.Mappers;

import org.Almacen.TopAlmacen.DTO.Categoria.CategoriaDto;
import org.Almacen.TopAlmacen.DTO.Categoria.CreateCategoriaDto;
import org.Almacen.TopAlmacen.DTO.Categoria.UpdateCategoriaDto;
import org.Almacen.TopAlmacen.Model.Categoria;

import java.util.List;
import java.util.stream.Collectors;

public class CategoriaMapper {

    public static CategoriaDto toDto(Categoria categoria) {
        if (categoria == null) {
            return null;
        } else {
            CategoriaDto dto = new CategoriaDto();
            dto.setId(categoria.getId());
            dto.setNombre(categoria.getNombre());
            dto.setDescripcion(categoria.getDescripcion());
            dto.setEstado(categoria.getEstado());
            dto.setFechaRegistro(categoria.getFechaRegistro());
            return dto;
        }
    }
    public static List<CategoriaDto> toDTOList(List<Categoria> categorias) {
        return categorias.stream()
                .map(CategoriaMapper::toDto)
                .collect(Collectors.toList());
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
