package org.Almacen.TopAlmacen.DTO.ProductoProveedorEntrada;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.Almacen.TopAlmacen.DTO.Empresa.EmpresaDto;
import org.Almacen.TopAlmacen.DTO.Usuario.UsuarioDto;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProductoProveedorEntradaDto {
    private EmpresaDto empresaDto;
    private UsuarioDto usuarioDto;
    private double precioFinal;
}
