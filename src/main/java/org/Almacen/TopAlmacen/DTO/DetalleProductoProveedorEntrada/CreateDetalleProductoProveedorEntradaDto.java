package org.Almacen.TopAlmacen.DTO.DetalleProductoProveedorEntrada;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.Almacen.TopAlmacen.Model.Producto;
import org.Almacen.TopAlmacen.Model.ProductoProveedorEntrada;
import org.Almacen.TopAlmacen.Model.TipoUnidad;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateDetalleProductoProveedorEntradaDto {
    private ProductoProveedorEntrada OC_id;
    private double cantidad;
    private TipoUnidad tipoUnidad;
    private String descripcion;
    private double descuento;
    private double precioUnitario;
    private double precioTotal;
}
