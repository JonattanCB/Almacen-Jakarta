package org.Almacen.TopAlmacen.DTO.Requerimiento;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.Almacen.TopAlmacen.Model.UnidadDependencia;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRequerimientoDto {
    private String estado;
    private String RazonEntrada;
    private String RazonSalida;
}
