package org.Almacen.TopAlmacen.DTO.Rol;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.Almacen.TopAlmacen.Model.UnidadDependencia;
import org.Almacen.TopAlmacen.Model.Usuario;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class RolConUsuariosDto {
    private int id;
    private String nombre;
    private String estado;
    private LocalDate fechaRegistro;
    private List<Usuario> usuarios;
    private UnidadDependencia unidadDependencia;
}
