package org.Almacen.TopAlmacen.DTO.HistorialPrecios;

import org.Almacen.TopAlmacen.Model.PrecioPorTipoUnidad;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class HistorialPreciosDto {
    private int id;
    private PrecioPorTipoUnidad precioPorTipoUnidad;
    private double precioRegistro;
    private LocalDateTime fechaRegistro;
}
