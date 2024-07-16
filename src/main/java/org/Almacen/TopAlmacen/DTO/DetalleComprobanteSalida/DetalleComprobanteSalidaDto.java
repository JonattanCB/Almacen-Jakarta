package org.Almacen.TopAlmacen.DTO.DetalleComprobanteSalida;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.Almacen.TopAlmacen.Model.ComprobanteSalida;
import org.Almacen.TopAlmacen.Model.Producto;
import org.Almacen.TopAlmacen.Model.TipoUnidad;

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
    private double precioUnitario;
    private double precioTotal;
}
