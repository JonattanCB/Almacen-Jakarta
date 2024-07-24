package org.Almacen.Siman.Mappers;

import org.Almacen.Siman.DTO.ProductoProveedorEntrada.CreateProductoProveedorEntradaDto;
import org.Almacen.Siman.DTO.ProductoProveedorEntrada.ProductoProveedorEntradaDto;
import org.Almacen.Siman.Model.ProductoProveedorEntrada;

public class ProductoProveedorEntradaMapper {
    public static ProductoProveedorEntradaDto toDto(ProductoProveedorEntrada p) {
        return new ProductoProveedorEntradaDto(p.getOC(), p.getEmpresa(), p.getFechaRegistro(), p.getUsuario(), p.getPrecioFinal(), p.getObservacion(), p.getEstado(),p.getAprobadoPor());
    }

    public static ProductoProveedorEntrada toEntity(ProductoProveedorEntradaDto dto) {
        ProductoProveedorEntrada p = new ProductoProveedorEntrada();
        p.setOC(dto.getOC());
        p.setEmpresa(dto.getEmpresa());
        p.setUsuario(dto.getUsuario());
        p.setPrecioFinal(dto.getPrecioFinal());
        p.setObservacion(dto.getObservacion());
        p.setEstado(dto.getEstado());
        p.setAprobadoPor(dto.getAprobadoPor());
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
