package org.Almacen.TopAlmacen.DTO.DetalleComprobanteSalida;

import org.Almacen.TopAlmacen.Model.ComprobanteSalida;
import org.Almacen.TopAlmacen.Model.TipoUnidad;

public class CreateDetalleComprobanteSalidaDto {
    private ComprobanteSalida comprobanteSalida;
    private double cantidad;
    private TipoUnidad tipoUnidad;
    private String descripcion;
    private double precioUnitario;
    private double precioTotal;
}