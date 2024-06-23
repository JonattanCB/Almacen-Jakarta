package org.Almacen.TopAlmacen.DTO.Producto;

import org.Almacen.TopAlmacen.DTO.Categoria.CategoriaDto;
import org.Almacen.TopAlmacen.DTO.Marca.MarcaDto;

import java.time.LocalDate;

public class CreateProductoDto {
    private String nombre;
    private String color;
    private String peso;
    private CategoriaDto categoriaDto;
    private MarcaDto marcaDto;
}
