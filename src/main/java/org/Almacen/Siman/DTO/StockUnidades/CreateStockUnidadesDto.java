package org.Almacen.Siman.DTO.StockUnidades;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.Almacen.Siman.Model.PrecioPorTipoUnidad;

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
