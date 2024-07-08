package org.Almacen.TopAlmacen.Mappers;

import org.Almacen.TopAlmacen.DTO.DetalleProductoProveedorEntrada.CreateDetalleProductoProveedorEntradaDto;
import org.Almacen.TopAlmacen.DTO.DetalleProductoProveedorEntrada.DetalleProductoProveedorEntradaDto;
import org.Almacen.TopAlmacen.DTO.DetalleProductoProveedorEntrada.ListaDetalleProductoProveedorEntradaDto;
import org.Almacen.TopAlmacen.DTO.DetalleProductoProveedorEntrada.UpdateDetalleProductoProveedorEntradaDto;
import org.Almacen.TopAlmacen.Model.DetalleProductoProveedorEntrada;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DetalleProductoProveedorEntradaMapper {
    public static DetalleProductoProveedorEntradaDto toDto(DetalleProductoProveedorEntrada d) {
        return new DetalleProductoProveedorEntradaDto(d.getId(), d.getOC_id(), d.getCantidad(), d.getTipoUnidad(), d.getPrecioPorTipoUnidad(), d.getPrecioUnitario(), d.getPrecioTotal());
    }

    public static List<CreateDetalleProductoProveedorEntradaDto> toDtoCreate(List<ListaDetalleProductoProveedorEntradaDto> d) {
        List<CreateDetalleProductoProveedorEntradaDto> lst = new ArrayList<>();
        for (ListaDetalleProductoProveedorEntradaDto l : d) {
            CreateDetalleProductoProveedorEntradaDto dto = new CreateDetalleProductoProveedorEntradaDto();
            dto.setCantidad(l.getCantidad());
            dto.setTipoUnidad(l.getTipoUnidad());
            dto.setPrecioTotal(l.getPrecioTotal());
            dto.setPrecioPorTipoUnidad(l.getPrecioPorTipoUnidad());
            dto.setPrecioUnitario(l.getPrecioUnitario());
            dto.setOC_id(l.getOC_id());
            lst.add(dto);
        }
        return lst;
    }


    public static DetalleProductoProveedorEntrada fromCreate(CreateDetalleProductoProveedorEntradaDto d) {
        DetalleProductoProveedorEntrada e = new DetalleProductoProveedorEntrada();
        e.setOC_id(d.getOC_id());
        e.setCantidad(d.getCantidad());
        e.setTipoUnidad(d.getTipoUnidad());
        e.setPrecioPorTipoUnidad(d.getPrecioPorTipoUnidad());
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
        e.setPrecioPorTipoUnidad(d.getPrecioPorTipoUnidad());
        e.setPrecioUnitario(d.getPrecioUnitario());
        e.setPrecioTotal(d.getPrecioUnitario() * d.getCantidad());
        return e;
    }
}
