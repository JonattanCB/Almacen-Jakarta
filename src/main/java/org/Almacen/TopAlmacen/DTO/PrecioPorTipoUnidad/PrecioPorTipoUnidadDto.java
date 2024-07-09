package org.Almacen.TopAlmacen.DTO.PrecioPorTipoUnidad;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.Almacen.TopAlmacen.Model.Producto;
import org.Almacen.TopAlmacen.Model.StockUnidades;
import org.Almacen.TopAlmacen.Model.TipoUnidad;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PrecioPorTipoUnidadDto {
    private int id;
    private TipoUnidad tipoUnidad;
    private Producto producto;
    private double precioUnitario;
    private double unidadesPorTipoUnidadPorProducto;
    private StockUnidades stockUnidades;

}
