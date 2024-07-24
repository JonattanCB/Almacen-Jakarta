package org.Almacen.Siman.DTO.DetalleProductoProveedorEntrada;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.Almacen.Siman.Model.PrecioPorTipoUnidad;
import org.Almacen.Siman.Model.ProductoProveedorEntrada;
import org.Almacen.Siman.Model.TipoUnidad;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateDetalleProductoProveedorEntradaDto {
    private int id;
    private ProductoProveedorEntrada OC_id;
    private double cantidad;
    private TipoUnidad tipoUnidad;
    private PrecioPorTipoUnidad precioPorTipoUnidad;
    private double precioUnitario;
    private double precioTotal;
}
