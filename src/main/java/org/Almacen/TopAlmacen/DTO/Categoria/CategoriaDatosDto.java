package org.Almacen.TopAlmacen.DTO.Categoria;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaDatosDto {
    private int id;
    private String nombre;
    private String descripcion;
    private String estado;
}
