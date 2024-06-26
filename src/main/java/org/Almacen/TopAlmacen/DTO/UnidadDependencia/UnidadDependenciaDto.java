package org.Almacen.TopAlmacen.DTO.UnidadDependencia;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.Almacen.TopAlmacen.Model.Dependencia;
import org.Almacen.TopAlmacen.Model.Usuario;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UnidadDependenciaDto {
    private int id;
    private Dependencia dependencia;
    private String nombre;
    private LocalDate fechaRegistro;

}
