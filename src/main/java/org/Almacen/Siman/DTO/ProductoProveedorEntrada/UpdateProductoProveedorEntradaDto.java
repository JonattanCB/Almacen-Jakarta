package org.Almacen.Siman.DTO.ProductoProveedorEntrada;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.Almacen.Siman.Model.Empresa;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProductoProveedorEntradaDto {
    private Empresa empresa;
    private double precioFinal;
    private String Observacion;
    private String estado;
}
