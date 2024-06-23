package org.Almacen.TopAlmacen.DTO.ProductoProveedorEntrada;

import org.Almacen.TopAlmacen.DTO.Empresa.EmpresaDto;
import org.Almacen.TopAlmacen.DTO.Usuario.UsuarioDto;
import org.Almacen.TopAlmacen.Model.Empresa;

import java.time.LocalDate;

public class ProductoProveedorEntradaDto {
    private int OC;
    private EmpresaDto empresaDto;
    private LocalDate fechaRegistro;
    private UsuarioDto usuarioDto;
    private double precioFinal;
}
