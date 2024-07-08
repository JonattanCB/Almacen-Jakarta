package org.Almacen.TopAlmacen.DTO.StockUnidades;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.Almacen.TopAlmacen.Model.PrecioPorTipoUnidad;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StockUnidadesDto {
    private int id;
    private double CantidadStockUnidad;
    private String tipoUnidad;
}
