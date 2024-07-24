package org.Almacen.Siman.DTO.DetalleComprobanteSalida;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.Almacen.Siman.Model.ComprobanteSalida;
import org.Almacen.Siman.Model.PrecioPorTipoUnidad;
import org.Almacen.Siman.Model.Producto;
import org.Almacen.Siman.Model.TipoUnidad;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DetalleComprobanteSalidaDto {
    private int id;
    private ComprobanteSalida comprobanteSalida;
    private double cantidad;
    private TipoUnidad tipoUnidad;
    private Producto producto;
    private PrecioPorTipoUnidad precioPorTipoUnidad;
    private String descripcionProducto;
    private double precioUnitario;
    private double precioTotal;
}
