package org.Almacen.TopAlmacen.DTO.Dependencia;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.Almacen.TopAlmacen.DTO.UnidadDependencia.UnidadDependenciaDto;
import org.Almacen.TopAlmacen.Model.UnidadDependencia;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class DependenciaConUnidadesDto {
    private int id;
    private String nombre;
    private String estado;
    private LocalDate fechaRegistro;
    private List<UnidadDependenciaDto>unidades;
}
