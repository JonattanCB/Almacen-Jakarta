package org.Almacen.TopAlmacen.DTO.ComprobanteSalida;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.Almacen.TopAlmacen.DTO.Dependencia.DependenciaDto;
import org.Almacen.TopAlmacen.DTO.Usuario.UsuarioDto;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ComprobanteSalidaDto {
    private int id;
    private DependenciaDto dependenciaDto;
    private String paraUso;
    private String observacion;
    private LocalDate fechaRegistro;
    private double precioFinal;
}
