package org.Almacen.Siman.DTO.Requerimiento;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRequerimientoDto {
    private String estado;
    private String RazonEntrada;
    private String RazonSalida;
}
