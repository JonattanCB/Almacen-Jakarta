package org.Almacen.TopAlmacen.DTO.Rol;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.Almacen.TopAlmacen.Model.UnidadDependencia;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RolDto {
    private int id;
    private String nombre;
    private String estado;
    private LocalDate fechaRegistro;
    private UnidadDependencia unidadDependencia;
}
