package org.Almacen.TopAlmacen.DTO.Requerimiento;

import org.Almacen.TopAlmacen.DTO.UnidadDependencia.UnidadDependenciaDto;
import org.Almacen.TopAlmacen.Model.UnidadDependencia;

import java.time.LocalDateTime;

public class RequerimientoDto {
    private int id;
    private LocalDateTime fechaRegistrada;
    private UnidadDependenciaDto unidadDependenciaDto;
    private String estado;
    private String Razon;
}
