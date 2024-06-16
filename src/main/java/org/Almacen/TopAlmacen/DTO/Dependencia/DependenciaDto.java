package org.Almacen.TopAlmacen.DTO.Dependencia;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DependenciaDto {
    private int id;
    private String nombre;
    private String ubicacion;
    private String estado;
    private LocalDate fechaRegistro;
}
