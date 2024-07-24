package org.Almacen.Siman.DTO.DetalleProductoProveedorEntrada;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.Almacen.Siman.Model.PrecioPorTipoUnidad;
import org.Almacen.Siman.Model.ProductoProveedorEntrada;
import org.Almacen.Siman.Model.TipoUnidad;

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
