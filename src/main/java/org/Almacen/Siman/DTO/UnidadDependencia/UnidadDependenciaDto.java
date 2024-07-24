package org.Almacen.Siman.DTO.UnidadDependencia;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.Almacen.Siman.Model.Dependencia;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UnidadDependenciaDto {
    private int id;
    private Dependencia dependencia;
    private String nombre;
    private LocalDate fechaRegistro;
}
