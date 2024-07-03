package org.Almacen.TopAlmacen.Mappers;

import org.Almacen.TopAlmacen.DTO.DetalleProductoProveedorEntrada.CreateDetalleProductoProveedorEntradaDto;
import org.Almacen.TopAlmacen.DTO.DetalleProductoProveedorEntrada.DetalleProductoProveedorEntradaDto;
import org.Almacen.TopAlmacen.Model.DetalleProductoProveedorEntrada;

public class DetalleProductoProveedorEntradaMapper {
    public static DetalleProductoProveedorEntradaDto toDto(DetalleProductoProveedorEntrada d) {
        return new DetalleProductoProveedorEntradaDto(d.getId(), d.getOC_id(), d.getCantidad(), d.getTipoUnidad(), d.getDescripcion(), d.getDescuento(), d.getPrecioUnitario(), d.getPrecioTotal());
    }

    public static DetalleProductoProveedorEntrada fromCreate(CreateDetalleProductoProveedorEntradaDto d) {
        DetalleProductoProveedorEntrada e = new DetalleProductoProveedorEntrada();
        e.setOC_id(d.getOC_id());
        e.setCantidad(d.getCantidad());
        e.setTipoUnidad(d.getTipoUnidad());
        e.setDescripcion(d.getDescripcion());
        e.setDescuento(d.getDescuento());
        e.setPrecioUnitario(d.getPrecioUnitario());
        e.setPrecioTotal(d.getPrecioUnitario() * d.getCantidad());
        return e;
    }
}
