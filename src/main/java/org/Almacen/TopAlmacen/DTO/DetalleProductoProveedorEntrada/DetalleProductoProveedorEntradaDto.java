package org.Almacen.TopAlmacen.DTO.DetalleProductoProveedorEntrada;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.Almacen.TopAlmacen.Model.PrecioPorTipoUnidad;
import org.Almacen.TopAlmacen.Model.ProductoProveedorEntrada;
import org.Almacen.TopAlmacen.Model.TipoUnidad;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DetalleProductoProveedorEntradaDto {
    private int id;
    private ProductoProveedorEntrada OC_id;
    private double cantidad;
    private TipoUnidad tipoUnidad;
    private PrecioPorTipoUnidad precioPorTipoUnidad;
    private double precioUnitario;
    private double precioTotal;
}
