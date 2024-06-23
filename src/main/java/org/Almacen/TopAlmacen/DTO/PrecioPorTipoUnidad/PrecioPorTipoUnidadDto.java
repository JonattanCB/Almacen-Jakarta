package org.Almacen.TopAlmacen.DTO.PrecioPorTipoUnidad;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.Almacen.TopAlmacen.DTO.Producto.ProductoDto;
import org.Almacen.TopAlmacen.DTO.TipoUnidad.TipoUnidadDto;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PrecioPorTipoUnidadDto {
    private int id;
    private TipoUnidadDto tipoUnidadDto;
    private ProductoDto productoDto;
    private double precio;
    private double unidadesPorTipoUnidadPorProducto;

}
