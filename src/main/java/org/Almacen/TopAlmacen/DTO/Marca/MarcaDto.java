package org.Almacen.TopAlmacen.DTO.Marca;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class MarcaDto {
    private int id;
    private String nombre;
    private String estado;
    private LocalDate fechaRegistro;
}
