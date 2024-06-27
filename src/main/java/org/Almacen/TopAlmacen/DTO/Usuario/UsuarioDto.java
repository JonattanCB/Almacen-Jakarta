package org.Almacen.TopAlmacen.DTO.Usuario;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.Almacen.TopAlmacen.DTO.Rol.RolDto;
import org.Almacen.TopAlmacen.DTO.UnidadDependencia.UnidadDependenciaDto;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDto {
    private int id;
    private String nombres;
    private String apellidos;
    private String correo;
    private String contra;
    private String estado;
    private RolDto rolDto;
    private LocalDate fechaRegistro;
}
