package org.Almacen.TopAlmacen.DTO.MovimientoStock;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.Almacen.TopAlmacen.Model.PrecioPorTipoUnidad;
import org.Almacen.TopAlmacen.Model.TipoUnidad;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovimientoStockDto {
    private int id;
    private LocalDateTime fechaRegistro;
    private String tipoMovimiento;
    private String precioPorTipoUnidad;
    private double cantidad;
    private String tipoUnidad;
}
