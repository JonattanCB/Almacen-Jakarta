package org.Almacen.TopAlmacen.DTO.DetalleComprobanteSalida;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.Almacen.TopAlmacen.DTO.ComprobanteSalida.ComprobanteSalidaDto;
import org.Almacen.TopAlmacen.DTO.Producto.ProductoDto;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DetalleComprobanteSalidaDto {
    private int id;
    private ComprobanteSalidaDto comprobanteSalidaDto;
    private ProductoDto productoDto;
    private double cantidad;
}
