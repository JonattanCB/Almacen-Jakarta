package org.Almacen.Siman.DTO.RolPermiso;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.Almacen.Siman.DTO.Permiso.PermisoDto;
import org.Almacen.Siman.DTO.Rol.RolDto;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRolPermisoDto {
    private RolDto rolDto;
    private PermisoDto permisoDto;
}
