package org.Almacen.TopAlmacen.Mappers;

import org.Almacen.TopAlmacen.DAO.IProductoProveedorEntradaDao;
import org.Almacen.TopAlmacen.DTO.ProductoProveedorEntrada.CreateProductoProveedorEntradaDto;
import org.Almacen.TopAlmacen.DTO.ProductoProveedorEntrada.ProductoProveedorEntradaDto;
import org.Almacen.TopAlmacen.Model.ProductoProveedorEntrada;

public class ProductoProveedorEntradaMapper {
    public static ProductoProveedorEntradaDto toDto(ProductoProveedorEntrada p) {
        return new ProductoProveedorEntradaDto(p.getOC(), p.getEmpresa(), p.getFechaRegistro(), p.getUsuario(), p.getPrecioFinal());
    }

    public static ProductoProveedorEntrada toEntity(CreateProductoProveedorEntradaDto dto){
        var product = new ProductoProveedorEntrada();
        product.setOC(dto.getOC());
        product.setEmpresa(dto.getEmpresa());
        product.setUsuario(dto.getUsuario());
        product.setPrecioFinal(dto.getPrecioFinal());
        return new ProductoProveedorEntrada();
    }

    public static ProductoProveedorEntrada fromCreate(CreateProductoProveedorEntradaDto dto) {
        ProductoProveedorEntrada p = new ProductoProveedorEntrada();
        p.setEmpresa(dto.getEmpresa());
        p.setUsuario(dto.getUsuario());
        p.setPrecioFinal(dto.getPrecioFinal());
        return p;
    }
}
