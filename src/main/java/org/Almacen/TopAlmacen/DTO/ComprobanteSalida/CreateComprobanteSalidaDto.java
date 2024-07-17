package org.Almacen.TopAlmacen.DTO.ComprobanteSalida;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.Almacen.TopAlmacen.Model.UnidadDependencia;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CreateComprobanteSalidaDto {

    private UnidadDependencia unidadDependencia;
    private String paraUso;
    private String observacion;
    private double precioFinal;

}
