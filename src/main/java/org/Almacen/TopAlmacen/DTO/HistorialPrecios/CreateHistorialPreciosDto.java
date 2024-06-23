package org.Almacen.TopAlmacen.DTO.HistorialPrecios;

import org.Almacen.TopAlmacen.Model.PrecioPorTipoUnidad;

import java.time.LocalDateTime;

public class CreateHistorialPreciosDto {
    private PrecioPorTipoUnidad precioPorTipoUnidad;
    private double precioRegistro;
    private LocalDateTime fechaRegistro;
}
