package org.Almacen.Siman.Mappers;

import org.Almacen.Siman.DTO.Requerimiento.CreateRequerimientoDto;
import org.Almacen.Siman.DTO.Requerimiento.RequerimientoDto;
import org.Almacen.Siman.Model.Requerimiento;

public class RequerimientoMapper {
    public static RequerimientoDto toDto(Requerimiento requerimiento) {
        RequerimientoDto requerimientoDto = new RequerimientoDto();
        requerimientoDto.setId(requerimiento.getId());
        requerimientoDto.setFechaRegistrada(requerimiento.getFechaRegistrada());
        requerimientoDto.setUsuario(requerimiento.getSolicitante());
        requerimientoDto.setEstado(requerimiento.getEstado());
        requerimientoDto.setRazonEntrada(requerimiento.getRazonEntrada());
        requerimientoDto.setRazonSalida(requerimiento.getRazonSalida());
        requerimientoDto.setRequerimiento(requerimiento.getItemsRequerimientos());
        requerimientoDto.setDependencia(requerimiento.getDependencia());
        return requerimientoDto;
    }

    public static Requerimiento toEntity(RequerimientoDto requerimientoDto) {
        Requerimiento requerimiento = new Requerimiento();
        requerimiento.setId(requerimientoDto.getId());
        requerimiento.setSolicitante(requerimientoDto.getUsuario());
        requerimiento.setEstado(requerimientoDto.getEstado());
        requerimiento.setFechaRegistrada(requerimientoDto.getFechaRegistrada());
        requerimiento.setRazonEntrada(requerimientoDto.getRazonEntrada());
        requerimiento.setRazonSalida(requerimientoDto.getRazonSalida());
        requerimiento.setItemsRequerimientos(requerimientoDto.getRequerimiento());
        requerimiento.setDependencia(requerimientoDto.getDependencia());
        return requerimiento;
    }


    public static Requerimiento fromCreate(CreateRequerimientoDto dto) {
        Requerimiento requerimiento = new Requerimiento();
        requerimiento.setId(dto.getId());
        requerimiento.setEstado("PENDIENTE");
        requerimiento.setSolicitante(dto.getUsuario());
        requerimiento.setRazonEntrada(dto.getRazonEntrada());
        requerimiento.setDependencia(dto.getDependencia());
        return requerimiento;
    }

}
