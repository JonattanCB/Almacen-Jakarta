package org.Almacen.TopAlmacen.Mappers;

import org.Almacen.TopAlmacen.DTO.DetalleProductoProveedorEntrada.CreateDetalleProductoProveedorEntradaDto;
import org.Almacen.TopAlmacen.DTO.DetalleProductoProveedorEntrada.DetalleProductoProveedorEntradaDto;
import org.Almacen.TopAlmacen.DTO.DetalleProductoProveedorEntrada.UpdateDetalleProductoProveedorEntradaDto;
import org.Almacen.TopAlmacen.Model.DetalleProductoProveedorEntrada;

import java.util.List;
import java.util.stream.Collectors;

public class DetalleProductoProveedorEntradaMapper {
    public static DetalleProductoProveedorEntradaDto toDto(DetalleProductoProveedorEntrada d) {
        return new DetalleProductoProveedorEntradaDto(d.getId(), d.getOC_id(), d.getCantidad(), d.getTipoUnidad(), d.getDescripcion(), d.getPrecioUnitario(), d.getPrecioTotal());
    }

    public static DetalleProductoProveedorEntrada fromCreate(CreateDetalleProductoProveedorEntradaDto d) {
        DetalleProductoProveedorEntrada e = new DetalleProductoProveedorEntrada();
        e.setOC_id(d.getOC_id());
        e.setCantidad(d.getCantidad());
        e.setTipoUnidad(d.getTipoUnidad());
        e.setDescripcion(ProductoMapper.toConcatProduct(d.getPrecioPorTipoUnidad().getProducto()));
        e.setPrecioUnitario(d.getPrecioUnitario());
        e.setPrecioTotal(d.getPrecioUnitario() * d.getCantidad());
        return e;
    }

    public static DetalleProductoProveedorEntrada toEntity(DetalleProductoProveedorEntradaDto d) {
        DetalleProductoProveedorEntrada e = new DetalleProductoProveedorEntrada();
        e.setId(d.getId());
        e.setOC_id(d.getOC_id());
        e.setCantidad(d.getCantidad());
        e.setTipoUnidad(d.getTipoUnidad());
        e.setDescripcion(d.getDescripcion());
        e.setPrecioUnitario(d.getPrecioUnitario());
        e.setPrecioTotal(d.getPrecioUnitario() * d.getCantidad());
        return e;
    }


}
