package org.Almacen.Siman.DTO.ComprobanteSalida;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.Almacen.Siman.Model.Dependencia;
import org.Almacen.Siman.Model.Usuario;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ComprobanteSalidaDto {
    private String id;
    private Dependencia dependencia;
    private String paraUso;
    private String observacion;
    private LocalDate fechaRegistro;
    private double precioFinal;
    private String estado;
    private Usuario solicitante;
    private String aprobadoPor;
}
