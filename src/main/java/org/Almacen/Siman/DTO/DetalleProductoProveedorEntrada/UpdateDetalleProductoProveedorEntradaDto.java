package org.Almacen.Siman.DTO.DetalleProductoProveedorEntrada;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.Almacen.Siman.Model.Producto;
import org.Almacen.Siman.Model.TipoUnidad;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateDetalleProductoProveedorEntradaDto {
    private double cantidad;
    private TipoUnidad tipoUnidad;
    private Producto producto;
    private double descuento;
    private double precioUnitario;
}
