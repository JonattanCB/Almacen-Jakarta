package org.Almacen.TopAlmacen.DTO.Requerimiento;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.Almacen.TopAlmacen.Model.ItemsRequerimiento;
import org.Almacen.TopAlmacen.Model.UnidadDependencia;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequerimientoDto {
    private String id;
    private LocalDateTime fechaRegistrada;
    private UnidadDependencia unidadDependencia;
    private String estado;
    private String RazonEntrada;
    private String RazonSalida;
    private List<ItemsRequerimiento> requerimiento;
}
