package org.Almacen.TopAlmacen.DTO.Rol;

import org.Almacen.TopAlmacen.DTO.Usuario.UsuarioDto;

import java.time.LocalDate;
import java.util.List;

public class RolConUsuariosDto {
    private int id;
    private String nombre;
    private String estado;
    private LocalDate fechaRegistro;
    private List<UsuarioDto>usuarioDtos;
}
