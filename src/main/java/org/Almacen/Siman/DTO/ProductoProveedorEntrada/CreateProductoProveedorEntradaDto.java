package org.Almacen.Siman.DTO.ProductoProveedorEntrada;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.Almacen.Siman.Model.Empresa;
import org.Almacen.Siman.Model.Usuario;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductoProveedorEntradaDto {
    private String OC;
    private Empresa empresa;
    private LocalDateTime fechaRegistro;
    private Usuario usuario;
    private double precioFinal;
    private String observacion;
    private String estado;
}
