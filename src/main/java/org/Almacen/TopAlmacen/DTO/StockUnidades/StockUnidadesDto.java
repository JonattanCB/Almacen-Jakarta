package org.Almacen.TopAlmacen.DTO.StockUnidades;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StockUnidadesDto {
    private int id;
    private String descripcion;
    private double CantidadStockUnidad;
    private String tipoUnidad;
}
