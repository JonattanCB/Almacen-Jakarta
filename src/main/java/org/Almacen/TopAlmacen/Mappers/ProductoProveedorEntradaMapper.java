package org.Almacen.TopAlmacen.Mappers;

import org.Almacen.TopAlmacen.DTO.ProductoProveedorEntrada.CreateProductoProveedorEntradaDto;
import org.Almacen.TopAlmacen.DTO.ProductoProveedorEntrada.ProductoProveedorEntradaDto;
import org.Almacen.TopAlmacen.Model.ProductoProveedorEntrada;

public class ProductoProveedorEntradaMapper {
    public static ProductoProveedorEntradaDto toDto(ProductoProveedorEntrada p) {
        return new ProductoProveedorEntradaDto(p.getOC(), p.getEmpresa(), p.getFechaRegistro(), p.getUsuario(), p.getPrecioFinal(), p.getObservacion(), p.getEstado());
    }

    public static ProductoProveedorEntrada toEntity(ProductoProveedorEntradaDto dto) {
        ProductoProveedorEntrada p = new ProductoProveedorEntrada();
        p.setOC(dto.getOC());
        p.setEmpresa(dto.getEmpresa());
        p.setUsuario(dto.getUsuario());
        p.setPrecioFinal(dto.getPrecioFinal());
        p.setObservacion(dto.getObservacion());
        p.setEstado(dto.getEstado());
        return p;
    }


    public static ProductoProveedorEntrada fromCreate(CreateProductoProveedorEntradaDto dto) {
        ProductoProveedorEntrada p = new ProductoProveedorEntrada();
        p.setOC(dto.getOC());
        p.setEmpresa(dto.getEmpresa());
        p.setUsuario(dto.getUsuario());
        p.setPrecioFinal(dto.getPrecioFinal());
        p.setObservacion(dto.getObservacion());
        p.setEstado("PENDIENTE");
        return p;
    }
}
