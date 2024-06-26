package org.Almacen.TopAlmacen.DTO.Rol;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.Almacen.TopAlmacen.Model.UnidadDependencia;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRolDto {
    private String nombre;
    private String estado;
    private UnidadDependencia unidadDependencia;
}
