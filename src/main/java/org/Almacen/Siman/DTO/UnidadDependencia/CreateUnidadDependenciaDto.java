package org.Almacen.Siman.DTO.UnidadDependencia;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.Almacen.Siman.Model.Dependencia;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateUnidadDependenciaDto {
    private Dependencia dependencia;
    private String nombre;
}
