package org.Almacen.TopAlmacen.Mappers;

import org.Almacen.TopAlmacen.DTO.Dependencia.CreateDependenciaDto;
import org.Almacen.TopAlmacen.DTO.Dependencia.DependenciaDto;
import org.Almacen.TopAlmacen.DTO.Dependencia.UpdateDependenciaDto;
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

    public static Dependencia toEntity(DependenciaDto dto) {
        return new Dependencia(dto.getId(),dto.getNombre(),dto.getEstado(), dto.getFechaRegistro(), null);
    }

    public static Dependencia toDependenciaFromCreate(CreateDependenciaDto dto) {
        Dependencia dependencia = new Dependencia();
        dependencia.setNombre(dto.getNombre());
        dependencia.setEstado(dto.getEstado());
        return dependencia;
    }

    public static Dependencia toDependenciaFromUpdate(UpdateDependenciaDto dto) {
        Dependencia dependencia = new Dependencia();
        dependencia.setNombre(dto.getNombre());
        dependencia.setEstado(dto.getEstado());
        return dependencia;
    }

}
