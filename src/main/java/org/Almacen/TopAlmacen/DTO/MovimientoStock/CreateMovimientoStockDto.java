package org.Almacen.TopAlmacen.DTO.MovimientoStock;

import org.Almacen.TopAlmacen.DTO.PrecioPorTipoUnidad.PrecioPorTipoUnidadDto;
import org.Almacen.TopAlmacen.Model.TipoUnidad;

public class CreateMovimientoStockDto {
    private String tipoMovimiento;
    private PrecioPorTipoUnidadDto precioPorTipoUnidadDto;
    private double cantidad;
    private TipoUnidad tipoUnidad;
}
