package org.Almacen.TopAlmacen.DTO.Usuario;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.Almacen.TopAlmacen.Model.Rol;
import org.Almacen.TopAlmacen.Model.UnidadDependencia;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CreateUsuarioDto {
    private String correo;
    private String contra;
    private String nombres;
    private String apellidos;
    private String estado;
    private UnidadDependencia unidad;
}
