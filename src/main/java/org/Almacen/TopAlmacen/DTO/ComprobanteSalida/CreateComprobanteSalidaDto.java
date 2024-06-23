package org.Almacen.TopAlmacen.DTO.ComprobanteSalida;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.Almacen.TopAlmacen.DTO.Dependencia.DependenciaDto;

import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CreateComprobanteSalidaDto {

    private DependenciaDto usuarioDto;
    private String paraUso;
    private String observacion;
    private double precioFinal;

}
