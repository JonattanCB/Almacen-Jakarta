package org.Almacen.TopAlmacen.DTO.PrecioPorTipoUnidad;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.Almacen.TopAlmacen.Model.Producto;
import org.Almacen.TopAlmacen.Model.TipoUnidad;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePrecioPorTipoUnidadDto {
    private TipoUnidad tipoUnidad;
    private Producto producto;
    private double precio;
    private double unidadesPorTipoUnidadPorProducto;
    private String estado;

}
