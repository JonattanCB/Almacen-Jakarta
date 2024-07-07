package org.Almacen.TopAlmacen.DTO.MovimientoStock;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.Almacen.TopAlmacen.Model.TipoUnidad;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateMovimientoStockDto {
    private String tipoMovimiento;
    private String descripcion;
    private double cantidad;
    private TipoUnidad tipoUnidad;
}
