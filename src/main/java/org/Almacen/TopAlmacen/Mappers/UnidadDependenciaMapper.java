package org.Almacen.TopAlmacen.Mappers;

import org.Almacen.TopAlmacen.DTO.UnidadDependencia.UnidadDependenciaDto;
import org.Almacen.TopAlmacen.Model.UnidadDependencia;

public class UnidadDependenciaMapper {
    public static UnidadDependenciaDto toDto(UnidadDependencia unidadDependencia) {
        UnidadDependenciaDto unidadDependenciaDto = new UnidadDependenciaDto();
        unidadDependenciaDto.setId(unidadDependencia.getId());
        unidadDependenciaDto.setDependencia(unidadDependencia.getDependencia());
        unidadDependenciaDto.setNombre(unidadDependencia.getNombre());
        unidadDependenciaDto.setFechaRegistro(unidadDependencia.getFechaRegistro());
        return unidadDependenciaDto;
    }
}
