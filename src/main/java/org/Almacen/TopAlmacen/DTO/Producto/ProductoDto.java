package org.Almacen.TopAlmacen.DTO.Producto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.Almacen.TopAlmacen.DTO.Categoria.CategoriaDto;
import org.Almacen.TopAlmacen.DTO.TipoUnidad.TipoUnidadDto;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductoDto {
    private int id;
    private String nombre;
    private String marca;
    private CategoriaDto categoria;
    private TipoUnidadDto tipoUnidadDto;
    private String estado;
    private LocalDate FechaRegistro;
}
