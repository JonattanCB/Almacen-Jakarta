package org.Almacen.TopAlmacen.Mappers;

import org.Almacen.TopAlmacen.DTO.Marca.MarcaDto;
import org.Almacen.TopAlmacen.Model.Marca;

public class MarcaMapper {
    public static MarcaDto toDto(Marca marca) {
        return new MarcaDto(marca.getId(), marca.getNombre(),marca.getDescripcion() ,marca.getFechaRegistro());
    }
}

