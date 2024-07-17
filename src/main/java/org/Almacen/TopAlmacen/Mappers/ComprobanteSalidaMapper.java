package org.Almacen.TopAlmacen.Mappers;

import org.Almacen.TopAlmacen.DTO.ComprobanteSalida.ComprobanteSalidaDto;
import org.Almacen.TopAlmacen.DTO.ComprobanteSalida.CreateComprobanteSalidaDto;
import org.Almacen.TopAlmacen.Model.ComprobanteSalida;

public class ComprobanteSalidaMapper {
    public static ComprobanteSalidaDto toDto(ComprobanteSalida cs) {
        return new ComprobanteSalidaDto(cs.getId(), cs.getUnidadDependencia(), cs.getParaUso(), cs.getObservacion(), cs.getFechaRegistro(), cs.getPrecioFinal());
    }

    public static ComprobanteSalida fromCreateDto(CreateComprobanteSalidaDto dto) {
        var cs = new ComprobanteSalida();
        cs.setObservacion(dto.getObservacion());
        cs.setParaUso(dto.getParaUso());
        cs.setUnidadDependencia(dto.getUnidadDependencia());
        cs.setPrecioFinal(dto.getPrecioFinal());
        return cs;
    }
}
