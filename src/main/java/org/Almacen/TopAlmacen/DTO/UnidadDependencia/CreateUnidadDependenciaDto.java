package org.Almacen.TopAlmacen.DTO.UnidadDependencia;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.Almacen.TopAlmacen.DTO.Dependencia.DependenciaDto;
import org.Almacen.TopAlmacen.DTO.Usuario.UsuarioDto;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateUnidadDependenciaDto {
    private DependenciaDto dependenciaDto;
    private String nombre;
    private UsuarioDto usuarioDto;
    private LocalDate fechaRegistro;
}
