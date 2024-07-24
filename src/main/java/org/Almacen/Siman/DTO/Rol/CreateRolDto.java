package org.Almacen.Siman.DTO.Rol;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.Almacen.Siman.Model.UnidadDependencia;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateRolDto {
    private String nombre;
    private String estado;
    private UnidadDependencia unidadDependencia;
}
