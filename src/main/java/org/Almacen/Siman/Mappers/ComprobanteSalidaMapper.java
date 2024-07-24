package org.Almacen.Siman.Mappers;

import org.Almacen.Siman.Controladores.ComprobanteSalida.PdfComprobanteSalidaDto;
import org.Almacen.Siman.DTO.ComprobanteSalida.ComprobanteSalidaDto;
import org.Almacen.Siman.DTO.ComprobanteSalida.CreateComprobanteSalidaDto;

import org.Almacen.Siman.Model.ComprobanteSalida;

public class ComprobanteSalidaMapper {
    public static ComprobanteSalidaDto toDto(ComprobanteSalida cs) {
        return new ComprobanteSalidaDto(cs.getId(), cs.getDependencia(), cs.getParaUso(), cs.getObservacion(), cs.getFechaRegistro(), cs.getPrecioFinal(), cs.getEstado(), cs.getUsuario());
    }

    public static ComprobanteSalida toEntity(ComprobanteSalidaDto cs) {
        return new ComprobanteSalida(cs.getId(), cs.getSolicitante(), cs.getDependencia(), cs.getParaUso(), cs.getFechaRegistro(), cs.getPrecioFinal(), cs.getObservacion(), null, cs.getEstado());
    }

    public static PdfComprobanteSalidaDto toPdfDto(ComprobanteSalida cs) {
        return new PdfComprobanteSalidaDto(cs.getFechaRegistro(),(cs.getUsuario().getNombres()+" "+cs.getUsuario().getApellidos()), cs.getDependencia().getNombre(),cs.getParaUso(),cs.getEstado(),cs.getPrecioFinal());
    }

    public static ComprobanteSalida fromCreateDto(CreateComprobanteSalidaDto dto) {
        var cs = new ComprobanteSalida();
        cs.setId(dto.getId());
        cs.setObservacion(dto.getObservacion());
        cs.setParaUso(dto.getParaUso());
        cs.setDependencia(dto.getDependencia());
        cs.setPrecioFinal(dto.getPrecioFinal());
        cs.setUsuario(dto.getSolicitante());
        cs.setEstado(dto.getEstado());
        return cs;
    }
}
