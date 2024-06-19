package org.Almacen.TopAlmacen.DTO.ComprobanteSalida;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.Almacen.TopAlmacen.DTO.Usuario.UsuarioDto;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CreateComprobanteSalidaDto {

    private String paraUso;
    private LocalDate fechaEntrega;
    private String estadoAprobacion;
    private String estadoDisponibilidad;
    private String observacion;
    private LocalDate fechaRegistro=LocalDate.now();
    private UsuarioDto usuarioDto;

}
