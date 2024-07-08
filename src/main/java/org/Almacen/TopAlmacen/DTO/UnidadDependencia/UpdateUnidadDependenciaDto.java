package org.Almacen.TopAlmacen.DTO.UnidadDependencia;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.Almacen.TopAlmacen.Model.Dependencia;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUnidadDependenciaDto {
    private Dependencia dependecia;
    private String nombre;
}
