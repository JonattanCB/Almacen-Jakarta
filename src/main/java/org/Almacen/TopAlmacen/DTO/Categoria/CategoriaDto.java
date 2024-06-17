package org.Almacen.TopAlmacen.DTO.Categoria;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.Almacen.TopAlmacen.Model.Producto;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaDto {
    private int id;
    private String nombre;
    private String descripcion;
    private int estado;
    private LocalDate FechaRegistro=LocalDate.now();
    private List<Producto> productos;

}
