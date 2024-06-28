package org.Almacen.TopAlmacen.DTO.Producto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.Almacen.TopAlmacen.Model.Categoria;
import org.Almacen.TopAlmacen.Model.Marca;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductoDto {
    private String nombre;
    private String color;
    private String peso;
    private Categoria categoria;
    private Marca marca;
}
