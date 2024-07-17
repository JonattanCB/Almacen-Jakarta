package org.Almacen.TopAlmacen.DTO.DetalleProductoProveedorEntrada;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.Almacen.TopAlmacen.Model.PrecioPorTipoUnidad;
import org.Almacen.TopAlmacen.Model.Producto;
import org.Almacen.TopAlmacen.Model.ProductoProveedorEntrada;
import org.Almacen.TopAlmacen.Model.TipoUnidad;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListadoDetalleProductoProveedorEntradaDto {
    private int id;
    private ProductoProveedorEntrada productoProveedorEntrada;
    private double cantidad;
    private TipoUnidad tipoUnidad;
    private PrecioPorTipoUnidad precioPorTipoUnidad;
    private String descripcionProducto;
    private double precioUniario;
    private double precioTotal;
}
