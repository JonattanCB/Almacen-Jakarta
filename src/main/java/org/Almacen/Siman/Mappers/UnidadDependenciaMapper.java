package org.Almacen.Siman.Mappers;

import org.Almacen.Siman.DTO.UnidadDependencia.CreateUnidadDependenciaDto;
import org.Almacen.Siman.DTO.UnidadDependencia.UnidadDependenciaDto;
import org.Almacen.Siman.DTO.UnidadDependencia.UpdateUnidadDependenciaDto;
import org.Almacen.Siman.Model.UnidadDependencia;

public class UnidadDependenciaMapper {
    public static UnidadDependenciaDto toDto(UnidadDependencia unidadDependencia) {
        var unidadDependenciaDto = new UnidadDependenciaDto();
        unidadDependenciaDto.setId(unidadDependencia.getId());
        unidadDependenciaDto.setDependencia(unidadDependencia.getDependencia());
        unidadDependenciaDto.setNombre(unidadDependencia.getNombre());
        unidadDependenciaDto.setFechaRegistro(unidadDependencia.getFechaRegistro());
        return unidadDependenciaDto;
    }

    public static UnidadDependencia toEntity(UnidadDependenciaDto unidadDependenciaDto) {
        var unidadDependencia = new UnidadDependencia();
        unidadDependencia.setId(unidadDependenciaDto.getId());
        unidadDependencia.setDependencia(unidadDependenciaDto.getDependencia());
        unidadDependencia.setNombre(unidadDependenciaDto.getNombre());
        unidadDependencia.setFechaRegistro(unidadDependenciaDto.getFechaRegistro());
        return unidadDependencia;
    }

    public static UnidadDependencia toUnidadDependenciaFromCreate(CreateUnidadDependenciaDto dto) {
        UnidadDependencia unidadDependencia = new UnidadDependencia();
        unidadDependencia.setNombre(dto.getNombre());
        unidadDependencia.setDependencia(dto.getDependencia());
        return unidadDependencia;
    }

    public static UnidadDependencia toUnidadDependenciaFromUpdate(UpdateUnidadDependenciaDto dto) {
        UnidadDependencia unidadDependencia = new UnidadDependencia();
        unidadDependencia.setNombre(dto.getNombre());
        unidadDependencia.setDependencia(dto.getDependecia());
        return unidadDependencia;
    }

}
