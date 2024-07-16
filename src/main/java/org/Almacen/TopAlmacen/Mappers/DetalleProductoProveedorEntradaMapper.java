package org.Almacen.TopAlmacen.Mappers;

import org.Almacen.TopAlmacen.DTO.DetalleProductoProveedorEntrada.*;
import org.Almacen.TopAlmacen.Model.DetalleProductoProveedorEntrada;

import java.util.ArrayList;
import java.util.List;

public class DetalleProductoProveedorEntradaMapper {
    public static DetalleProductoProveedorEntradaDto toDto(DetalleProductoProveedorEntrada d) {
        return new DetalleProductoProveedorEntradaDto(d.getId(), d.getOC_id(), d.getCantidad(), d.getTipoUnidad(), d.getProducto(), d.getPrecioUnitario(), d.getPrecioTotal());
    }

    public static ListaDetalleProductoProveedorEntradaDto toDtoLista(DetalleProductoProveedorEntrada d) {
        return new ListaDetalleProductoProveedorEntradaDto(d.getId(), d.getOC_id(), d.getCantidad(), d.getTipoUnidad(), d.getProducto(), ProductoMapper.toConcatProduct(d.getProducto()), d.getPrecioUnitario(), d.getPrecioTotal());
    }

    public static PdfDetalleProductoProveedorEntradaDto toDtoPdf(DetalleProductoProveedorEntrada d) {
        return new PdfDetalleProductoProveedorEntradaDto(d.getProducto().getId(), ProductoMapper.toConcatProduct(d.getProducto()), d.getTipoUnidad().getNombre(), d.getProducto().getMarca().getNombre(), d.getCantidad());
    }

    public static List<CreateDetalleProductoProveedorEntradaDto> toDtoCreate(List<ListaDetalleProductoProveedorEntradaDto> d) {
        List<CreateDetalleProductoProveedorEntradaDto> lst = new ArrayList<>();
        for (ListaDetalleProductoProveedorEntradaDto l : d) {
            var dto = new CreateDetalleProductoProveedorEntradaDto();
            dto.setCantidad(l.getCantidad());
            dto.setTipoUnidad(l.getTipoUnidad());
            dto.setPrecioTotal(l.getPrecioTotal());
            dto.setPrecioPorTipoUnidad(l.getProducto());

            dto.setPrecioUnitario(l.getPrecioUnitario());
            dto.setOC_id(l.getOC_id());
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
        System.out.println(d.getPrecioPorTipoUnidad().getProducto().getNombre() + "Encontrado");
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
