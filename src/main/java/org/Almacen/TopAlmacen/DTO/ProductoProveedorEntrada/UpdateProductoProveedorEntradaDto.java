package org.Almacen.TopAlmacen.DTO.ProductoProveedorEntrada;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.Almacen.TopAlmacen.Model.Empresa;
import org.Almacen.TopAlmacen.Model.Usuario;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProductoProveedorEntradaDto {
    private Empresa empresa;
    private Usuario usuario;
    private double precioFinal;
}
