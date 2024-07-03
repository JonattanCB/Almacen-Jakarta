package org.Almacen.TopAlmacen.DTO.HistorialPrecios;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.Almacen.TopAlmacen.Model.PrecioPorTipoUnidad;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HistorialPreciosDto {
    private int id;
    private String precioPorTipoUnidad;
    private double precioRegistro;
    private LocalDateTime fechaRegistro;
}
