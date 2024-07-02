package org.Almacen.TopAlmacen.DTO.Requerimiento;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.Almacen.TopAlmacen.DTO.UnidadDependencia.UnidadDependenciaDto;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequerimientoDto {
    private int id;
    private LocalDateTime fechaRegistrada;
    private UnidadDependenciaDto unidadDependenciaDto;
    private String estado;
    private String Razon;
}
