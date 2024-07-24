package org.Almacen.Siman.DTO.Producto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.Almacen.Siman.Model.Categoria;
import org.Almacen.Siman.Model.Marca;
import org.Almacen.Siman.Model.StockUnidades;

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
    private Categoria categoria;
    private Marca marca;
    private LocalDate fechaRegistro;
    private String estado;
    private StockUnidades stockUnidades;
}