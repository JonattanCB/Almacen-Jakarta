package org.Almacen.TopAlmacen.Mappers;

import org.Almacen.TopAlmacen.DTO.Categoria.CategoriaDto;
import org.Almacen.TopAlmacen.DTO.Categoria.CreateCategoriaDto;
import org.Almacen.TopAlmacen.DTO.Categoria.UpdateCategoriaDto;
import org.Almacen.TopAlmacen.DTO.Marca.CreateMarcaDto;
import org.Almacen.TopAlmacen.DTO.Marca.MarcaDto;
import org.Almacen.TopAlmacen.DTO.Marca.UpdateMarcaDto;
import org.Almacen.TopAlmacen.Model.Categoria;
import org.Almacen.TopAlmacen.Model.Marca;

import java.util.List;
import java.util.stream.Collectors;

public class MarcaMapper {
    public static MarcaDto toDto(Marca marca) {
        return new MarcaDto(marca.getId(),marca.getNombre(), marca.getEstado() ,marca.getFechaRegistro());
    }

    public static Marca toMarca(MarcaDto marcaDto) {
        Marca m = new Marca();
        m.setId(marcaDto.getId());
        m.setNombre(marcaDto.getNombre());
        m.setEstado(marcaDto.getEstado());
        m.setFechaRegistro(marcaDto.getFechaRegistro());
        return m;
    }

    public static List<MarcaDto> toDTOList(List<Marca> marcas) {
        return marcas.stream()
                .map(MarcaMapper::toDto)
                .collect(Collectors.toList());
    }

    public static Marca toMarcaFromCreate(CreateMarcaDto dto) {
        Marca marca = new Marca();
        marca.setNombre(dto.getNombre());
        marca.setEstado(dto.getEstado());
        return marca;
    }

    public static Marca toMarcaFromUpdate(UpdateMarcaDto dto) {
        Marca marca = new Marca();
        marca.setNombre(dto.getNombre());
        marca.setEstado(dto.getEstado());
        return marca;
    }


}

