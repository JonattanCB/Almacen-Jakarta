package org.Almacen.Siman.DTO.Dependencia;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CreateDependenciaDto {
    private String nombre;
    private String estado;
}
