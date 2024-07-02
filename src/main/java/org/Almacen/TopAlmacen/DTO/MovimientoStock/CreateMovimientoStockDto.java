package org.Almacen.TopAlmacen.DTO.MovimientoStock;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.Almacen.TopAlmacen.DTO.PrecioPorTipoUnidad.PrecioPorTipoUnidadDto;
import org.Almacen.TopAlmacen.Model.TipoUnidad;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateMovimientoStockDto {
    private String tipoMovimiento;
    private PrecioPorTipoUnidadDto precioPorTipoUnidadDto;
    private double cantidad;
    private TipoUnidad tipoUnidad;
}
