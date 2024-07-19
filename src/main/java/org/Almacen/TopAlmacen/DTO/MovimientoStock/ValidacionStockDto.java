package org.Almacen.TopAlmacen.DTO.MovimientoStock;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ValidacionStockDto {
    private int id;
    private String Descripcion;
    private String TipoUnidad;
    private double cantidad;
    private String observacion;

}
