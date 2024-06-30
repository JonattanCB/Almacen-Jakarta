package org.Almacen.TopAlmacen.DTO.StockUnidades;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class UpdateStockUnidadesDto {
    private double CantidadStockUnidadesDto;
    private String tipoUnidad;
}
