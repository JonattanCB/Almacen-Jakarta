package org.Almacen.Siman.DTO.Usuario;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.Almacen.Siman.Model.UnidadDependencia;

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
