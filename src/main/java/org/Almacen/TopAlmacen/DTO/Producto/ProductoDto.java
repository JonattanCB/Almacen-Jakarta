package org.Almacen.TopAlmacen.DTO.Producto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.Almacen.TopAlmacen.DTO.Categoria.CategoriaDto;
import org.Almacen.TopAlmacen.DTO.Marca.MarcaDto;
import org.Almacen.TopAlmacen.DTO.TipoUnidad.TipoUnidadDto;
import org.Almacen.TopAlmacen.Model.Marca;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductoDto {
    private int id;
    private String nombre;
    private String color;
    private String peso;
    private CategoriaDto categoriaDto;
    private MarcaDto marcaDto;
    private LocalDate fechaRegistro;
}
