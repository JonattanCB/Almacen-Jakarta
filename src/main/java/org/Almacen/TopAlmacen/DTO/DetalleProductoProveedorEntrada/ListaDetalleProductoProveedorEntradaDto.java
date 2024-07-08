package org.Almacen.TopAlmacen.DTO.DetalleProductoProveedorEntrada;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.Almacen.TopAlmacen.Model.PrecioPorTipoUnidad;
import org.Almacen.TopAlmacen.Model.ProductoProveedorEntrada;
import org.Almacen.TopAlmacen.Model.TipoUnidad;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListaDetalleProductoProveedorEntradaDto {
    private int id;
    private ProductoProveedorEntrada OC_id;
    private double cantidad;
    private TipoUnidad tipoUnidad;
    private PrecioPorTipoUnidad precioPorTipoUnidad;
    private String descripcion;
    private double precioUnitario;
    private double precioTotal;

}
