package org.Almacen.TopAlmacen.DTO.ComprobanteSalida;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.Almacen.TopAlmacen.DTO.UnidadDependencia.UnidadDependenciaDto;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CreateComprobanteSalidaDto {

    private UnidadDependenciaDto unidadDependenciaDto;
    private String paraUso;
    private String observacion;
    private double precioFinal;

}
