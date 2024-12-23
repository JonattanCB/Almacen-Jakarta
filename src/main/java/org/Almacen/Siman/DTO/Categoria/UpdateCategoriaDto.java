package org.Almacen.Siman.DTO.Categoria;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCategoriaDto {
    private String nombre;
    private String descripcion;
    private String estado;
}
