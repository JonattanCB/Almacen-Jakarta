package org.Almacen.Siman.DTO.Marca;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateMarcaDto {
    private String nombre;
    private String estado;
}
