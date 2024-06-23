package org.Almacen.TopAlmacen.DTO.MovimientoStock;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.Almacen.TopAlmacen.DTO.PrecioPorTipoUnidad.PrecioPorTipoUnidadDto;
import org.Almacen.TopAlmacen.Model.TipoUnidad;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovimientoStockDto {
    private int id;
    private LocalDate fechaRegistro;
    private String tipoMovimiento;
    private PrecioPorTipoUnidadDto precioPorTipoUnidadDto;
    private double cantidad;
    private TipoUnidad tipoUnidad;
}
