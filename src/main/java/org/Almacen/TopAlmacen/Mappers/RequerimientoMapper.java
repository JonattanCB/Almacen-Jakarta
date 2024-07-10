package org.Almacen.TopAlmacen.Mappers;

import org.Almacen.TopAlmacen.DTO.Requerimiento.CreateRequerimientoDto;
import org.Almacen.TopAlmacen.DTO.Requerimiento.RequerimientoDto;
import org.Almacen.TopAlmacen.DTO.Requerimiento.UpdateRequerimientoDto;
import org.Almacen.TopAlmacen.Model.Requerimiento;

public class RequerimientoMapper {
    public static RequerimientoDto toDto(Requerimiento requerimiento){
        RequerimientoDto requerimientoDto = new RequerimientoDto();
        requerimientoDto.setId(requerimiento.getId());
        requerimientoDto.setFechaRegistrada(requerimiento.getFechaRegistrada());
        requerimientoDto.setUnidadDependencia(requerimiento.getUnidadDependencia());
        requerimientoDto.setEstado(requerimiento.getEstado());
        requerimientoDto.setRazonEntrada(requerimiento.getRazonEntrada());
        requerimientoDto.setRazonSalida(requerimiento.getRazonSalida());
        return requerimientoDto;
    }
    public static Requerimiento toEntity(RequerimientoDto requerimientoDto){
        Requerimiento requerimiento = new Requerimiento();
        requerimiento.setId(requerimientoDto.getId());
        requerimiento.setUnidadDependencia(requerimientoDto.getUnidadDependencia());
        requerimiento.setEstado(requerimientoDto.getEstado());
        requerimiento.setFechaRegistrada(requerimientoDto.getFechaRegistrada());
        requerimiento.setRazonEntrada(requerimientoDto.getRazonEntrada());
        requerimiento.setRazonSalida(requerimientoDto.getRazonSalida());
        return requerimiento;
    }
    public static Requerimiento fromCreate(CreateRequerimientoDto dto){
        Requerimiento requerimiento = new Requerimiento();
        requerimiento.setEstado("PENDIENTE");
        requerimiento.setUnidadDependencia(dto.getUnidadDependencia());
        requerimiento.setRazonEntrada(dto.getRazonEntrada());
        return requerimiento;
    }
    public static Requerimiento fromUpdate(UpdateRequerimientoDto dto){
        Requerimiento requerimiento = new Requerimiento();
        dto.setEstado(dto.getEstado());
        dto.setRazonEntrada(dto.getRazonEntrada());
        dto.setRazonSalida(dto.getRazonSalida());
        return requerimiento;
    }
}
