package org.Almacen.TopAlmacen.DTO.StockUnidades;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.Almacen.TopAlmacen.DTO.PrecioPorTipoUnidad.PrecioPorTipoUnidadDto;
import org.Almacen.TopAlmacen.DTO.TipoUnidad.TipoUnidadDto;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class UpdateStockUnidadesDto {
    private PrecioPorTipoUnidadDto precioPorTipoUnidadDto;
    private double CantidadStockUnidadesDto;
    private TipoUnidadDto tipoUnidadDto;
}
