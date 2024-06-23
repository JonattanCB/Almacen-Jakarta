package org.Almacen.TopAlmacen.DTO.UnidadDependencia;

import org.Almacen.TopAlmacen.DTO.Dependencia.DependenciaDto;
import org.Almacen.TopAlmacen.DTO.Usuario.UsuarioDto;

import java.time.LocalDate;

public class UnidadDependenciaDto {
    private int id;
    private DependenciaDto dependenciaDto;
    private String nombre;
    private UsuarioDto usuarioDto;
    private LocalDate fechaRegistro;

}
