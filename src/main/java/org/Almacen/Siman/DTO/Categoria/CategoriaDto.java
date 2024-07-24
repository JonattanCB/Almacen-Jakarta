package org.Almacen.Siman.DTO.Categoria;

import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaDto {
    private int id;
    private String nombre;
    private String descripcion;
    private String estado;
    private LocalDate FechaRegistro;
}
