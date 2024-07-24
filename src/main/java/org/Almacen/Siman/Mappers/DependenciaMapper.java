package org.Almacen.Siman.Mappers;

import org.Almacen.Siman.DTO.Dependencia.CreateDependenciaDto;
import org.Almacen.Siman.DTO.Dependencia.DependenciaDto;
import org.Almacen.Siman.DTO.Dependencia.UpdateDependenciaDto;
import org.Almacen.Siman.Model.Dependencia;

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
