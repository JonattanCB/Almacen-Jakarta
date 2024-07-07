package org.Almacen.TopAlmacen.DTO.Requerimiento;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.Almacen.TopAlmacen.Model.UnidadDependencia;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateRequerimientoDto {
    private UnidadDependencia unidadDependencia;
    private String estado;
    private String RazonEntrada;
    private String RazonSalida;
}
