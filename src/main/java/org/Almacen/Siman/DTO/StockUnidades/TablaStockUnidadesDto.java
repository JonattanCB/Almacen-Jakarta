package org.Almacen.Siman.DTO.StockUnidades;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TablaStockUnidadesDto {
    private int id;
    private String Descripcion;
    private double CantidadStockUnidad;
    private String tipoUnidad;
}
