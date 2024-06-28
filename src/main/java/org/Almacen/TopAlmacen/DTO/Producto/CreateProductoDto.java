package org.Almacen.TopAlmacen.DTO.Producto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.Almacen.TopAlmacen.DTO.Categoria.CategoriaDto;
import org.Almacen.TopAlmacen.DTO.Marca.MarcaDto;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductoDto {
    private String nombre;
    private String color;
    private String peso;
    private CategoriaDto categoriaDto;
    private MarcaDto marcaDto;
}
