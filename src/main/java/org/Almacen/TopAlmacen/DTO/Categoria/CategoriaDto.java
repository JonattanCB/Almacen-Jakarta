package org.Almacen.TopAlmacen.DTO.Categoria;

import lombok.*;
import org.Almacen.TopAlmacen.DTO.Producto.ProductoDto;
import org.Almacen.TopAlmacen.Model.Producto;

import java.time.LocalDate;
import java.util.List;

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
