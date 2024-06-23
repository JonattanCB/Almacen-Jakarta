package org.Almacen.TopAlmacen.DTO.Usuario;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.Almacen.TopAlmacen.DTO.Rol.RolDto;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUsuarioDto {
    private String correo;
    private String contra;
    private String estado;
    private RolDto rolDto;
}
