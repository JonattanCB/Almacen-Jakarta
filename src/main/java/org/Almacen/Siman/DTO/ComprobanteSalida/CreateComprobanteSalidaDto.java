package org.Almacen.Siman.DTO.ComprobanteSalida;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.Almacen.Siman.Model.Dependencia;
import org.Almacen.Siman.Model.Usuario;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CreateComprobanteSalidaDto {
    private String id;
    private Dependencia dependencia;
    private String paraUso;
    private String observacion;
    private double precioFinal;
    private String estado;
    private Usuario solicitante;
}
