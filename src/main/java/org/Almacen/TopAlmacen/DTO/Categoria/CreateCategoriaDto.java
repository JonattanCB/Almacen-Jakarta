package org.Almacen.TopAlmacen.DTO.Categoria;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateCategoriaDto {
    private String nombre;
    private String descripcion;
    private String estado;
    private LocalTime FechaRegistro=LocalTime.now();
}
