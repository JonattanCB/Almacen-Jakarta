package org.Almacen.Siman.DTO.DetalleComprobanteSalida;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.Almacen.Siman.Model.ComprobanteSalida;
import org.Almacen.Siman.Model.PrecioPorTipoUnidad;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateDetalleComprobanteSalidaDto {
    private ComprobanteSalida comprobanteSalida;
    private double cantidad;
    private PrecioPorTipoUnidad precioPorTipoUnidad;
    private double precioUnitario;
    private double precioTotal;
}