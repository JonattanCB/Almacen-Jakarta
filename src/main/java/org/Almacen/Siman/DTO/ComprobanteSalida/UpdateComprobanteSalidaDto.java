package org.Almacen.Siman.DTO.ComprobanteSalida;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.Almacen.Siman.Model.Dependencia;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class UpdateComprobanteSalidaDto {
    private Dependencia dependencia;
    private String paraUso;
    private String observacion;
    private double precioFinal;
}
