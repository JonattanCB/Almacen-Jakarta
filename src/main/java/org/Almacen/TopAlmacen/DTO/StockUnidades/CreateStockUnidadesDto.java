package org.Almacen.TopAlmacen.DTO.StockUnidades;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.Almacen.TopAlmacen.Model.PrecioPorTipoUnidad;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CreateStockUnidadesDto {
    private double CantidadStockUnidadesDto;
    private String tipoUnidad;
    private List<PrecioPorTipoUnidad> precioPorTipoUnidad;
}
