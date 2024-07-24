package org.Almacen.Siman.DTO.Producto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.Almacen.Siman.Model.Categoria;
import org.Almacen.Siman.Model.Marca;


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
    private String estado = "ACTIVO";
}
