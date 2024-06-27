package org.Almacen.TopAlmacen.DTO.StockUnidades;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.Almacen.TopAlmacen.Model.PrecioPorTipoUnidad;
import org.Almacen.TopAlmacen.Model.TipoUnidad;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class UpdateStockUnidadesDto {
    private PrecioPorTipoUnidad precioPorTipoUnidad;
    private double CantidadStockUnidadesDto;
    private TipoUnidad tipoUnidad;
}
