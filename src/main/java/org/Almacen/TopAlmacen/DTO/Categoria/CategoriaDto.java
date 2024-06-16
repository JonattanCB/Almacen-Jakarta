package org.Almacen.TopAlmacen.DTO.Categoria;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaDto {
    private int id;
    private String nombre;
    private String descripcion;
    private int estado;
    private LocalDate FechaRegistro;
}
