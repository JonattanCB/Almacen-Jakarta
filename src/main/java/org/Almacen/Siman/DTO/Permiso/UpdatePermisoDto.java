package org.Almacen.Siman.DTO.Permiso;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePermisoDto {
    private String nombre;
    private String estado;
}
