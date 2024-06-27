package org.Almacen.TopAlmacen.DTO.Usuario;

import org.Almacen.TopAlmacen.DTO.Rol.RolDto;
import org.Almacen.TopAlmacen.DTO.UnidadDependencia.UnidadDependenciaDto;

import java.time.LocalDate;

public class UsuarioDto {
    private int id;
    private String correo;
    private String contra;
    private String estado;
    private RolDto rolDto;
    private LocalDate fechaRegistro;
}
