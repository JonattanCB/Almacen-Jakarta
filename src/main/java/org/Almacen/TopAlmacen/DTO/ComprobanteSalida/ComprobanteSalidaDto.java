package org.Almacen.TopAlmacen.DTO.ComprobanteSalida;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.Almacen.TopAlmacen.DTO.UnidadDependencia.UnidadDependenciaDto;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ComprobanteSalidaDto {
    private int id;
    private UnidadDependenciaDto unidadDependenciaDto;
    private String paraUso;
    private String observacion;
    private LocalDate fechaRegistro;
    private double precioFinal;
}
