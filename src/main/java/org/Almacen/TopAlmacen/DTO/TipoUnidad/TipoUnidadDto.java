package org.Almacen.TopAlmacen.DTO.TipoUnidad;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TipoUnidadDto {
    private int id;
    private String nombre;
    private String Abrev;
    private String estado;
    private LocalDate FechaRegistro;
}
