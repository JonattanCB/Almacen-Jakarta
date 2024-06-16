package org.Almacen.TopAlmacen.DTO.Usuario;

import org.Almacen.TopAlmacen.DTO.Dependencia.DependenciaDto;
import org.Almacen.TopAlmacen.DTO.Persona.PersonaDto;
import org.Almacen.TopAlmacen.DTO.Rol.RolDto;

import java.time.LocalDate;

public class UsuarioDto {
    private int id;
    private String correo;
    private String contra;
    private RolDto rolDto;
    private PersonaDto personaDto;
    private DependenciaDto dependenciaDto;
    private String foto;
    private String estado;
    private LocalDate fechaRegistro;
}
