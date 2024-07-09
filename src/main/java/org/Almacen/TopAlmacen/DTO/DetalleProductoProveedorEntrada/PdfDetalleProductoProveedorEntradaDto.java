package org.Almacen.TopAlmacen.DTO.DetalleProductoProveedorEntrada;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PdfDetalleProductoProveedorEntradaDto {
    private int id_pr;
    private String desc_pr;
    private String nom_unmd;
    private String marca;
    private double cantidad;
}
