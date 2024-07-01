package org.Almacen.TopAlmacen.Mappers;

import org.Almacen.TopAlmacen.DTO.Dependencia.DependenciaDto;
import org.Almacen.TopAlmacen.Model.Dependencia;

public class DependenciaMapper {
    public static DependenciaDto toDto(Dependencia dependencia) {
        DependenciaDto dto = new DependenciaDto();
        dto.setId(dependencia.getId());
        dto.setNombre(dependencia.getNombre());
        dto.setEstado(dependencia.getEstado());
        dto.setFechaRegistro(dependencia.getFechaRegistro());
        return dto;
    }
}
