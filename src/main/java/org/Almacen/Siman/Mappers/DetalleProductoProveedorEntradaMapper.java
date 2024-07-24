package org.Almacen.Siman.Mappers;

import org.Almacen.Siman.DTO.DetalleProductoProveedorEntrada.*;
import org.Almacen.Siman.Model.DetalleProductoProveedorEntrada;

import java.util.ArrayList;
import java.util.List;

public class DetalleProductoProveedorEntradaMapper {
    public static DetalleProductoProveedorEntradaDto toDto(DetalleProductoProveedorEntrada d) {
        return new DetalleProductoProveedorEntradaDto(d.getId(), d.getOC_id(), d.getCantidad(), d.getTipoUnidad(), d.getProducto(), d.getPrecioUnitario(), d.getPrecioTotal());
    }


    public static PdfDetalleProductoProveedorEntradaDto toDtoPdf(DetalleProductoProveedorEntrada d) {
        return new PdfDetalleProductoProveedorEntradaDto(d.getProducto().getId(), ProductoMapper.toConcatProduct(d.getProducto()), d.getTipoUnidad().getNombre(), d.getProducto().getMarca().getNombre(), d.getCantidad());
    }

    public static ListadoDetalleProductoProveedorEntradaDto toDtoLista(DetalleProductoProveedorEntrada d) {
        return new ListadoDetalleProductoProveedorEntradaDto(d.getId(),d.getOC_id(),d.getCantidad(),d.getTipoUnidad(),null,ProductoMapper.toConcatProduct(d.getProducto()),d.getPrecioUnitario(),d.getPrecioTotal());
    }


    public static List<CreateDetalleProductoProveedorEntradaDto> toDtoCreate(List<ListadoDetalleProductoProveedorEntradaDto> d) {
        List<CreateDetalleProductoProveedorEntradaDto> lst = new ArrayList<>();
        for (ListadoDetalleProductoProveedorEntradaDto l : d) {
            var dto = new CreateDetalleProductoProveedorEntradaDto();
            dto.setOC_id(l.getProductoProveedorEntrada());
            dto.setCantidad(l.getCantidad());
            dto.setTipoUnidad(l.getTipoUnidad());
            dto.setPrecioPorTipoUnidad(l.getPrecioPorTipoUnidad());
            dto.setPrecioUnitario(l.getPrecioUniario());
            dto.setPrecioTotal(l.getPrecioTotal());
            lst.add(dto);
        }
        return lst;
    }

    public static DetalleProductoProveedorEntrada fromCreate(CreateDetalleProductoProveedorEntradaDto d) {
        var e = new DetalleProductoProveedorEntrada();
        e.setOC_id(d.getOC_id());
        e.setCantidad(d.getCantidad());
        e.setTipoUnidad(d.getTipoUnidad());
        e.setProducto(d.getPrecioPorTipoUnidad().getProducto());
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
        e.setProducto(d.getProducto());
        e.setPrecioUnitario(d.getPrecioUnitario());
        e.setPrecioTotal(d.getPrecioUnitario() * d.getCantidad());
        return e;
    }
}
