package org.Almacen.TopAlmacen.DTO.Stock;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.Almacen.TopAlmacen.DTO.Producto.ProductoDto;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StockDto {
    private int id;
    private ProductoDto productoDto;
    private double cantidad;
    private LocalDate fechaRegistro;
}
