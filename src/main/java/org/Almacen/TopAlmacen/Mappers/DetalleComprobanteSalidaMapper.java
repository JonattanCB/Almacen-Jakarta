package org.Almacen.TopAlmacen.Mappers;

import org.Almacen.TopAlmacen.DTO.DetalleComprobanteSalida.CreateDetalleComprobanteSalidaDto;
import org.Almacen.TopAlmacen.DTO.DetalleComprobanteSalida.DetalleComprobanteSalidaDto;
import org.Almacen.TopAlmacen.DTO.DetalleProductoProveedorEntrada.DetalleProductoProveedorEntradaDto;
import org.Almacen.TopAlmacen.Model.DetalleComprobanteSalida;
import org.Almacen.TopAlmacen.Model.DetalleProductoProveedorEntrada;


public class DetalleComprobanteSalidaMapper {
    public static DetalleComprobanteSalidaDto toDto(DetalleComprobanteSalida d) {
        return new DetalleComprobanteSalidaDto(d.getId(), d.getComprobanteSalida(), d.getCantidad(), d.getTipoUnidad(), d.getProducto(), d.getPrecioUnitario(), d.getPrecioTotal());
    }

    public static DetalleComprobanteSalida fromCreate(CreateDetalleComprobanteSalidaDto d) {
        var detalle = new DetalleComprobanteSalida();
        detalle.setComprobanteSalida(d.getComprobanteSalida());
        detalle.setCantidad(d.getCantidad());
        detalle.setTipoUnidad(d.getPrecioPorTipoUnidad().getTipoUnidad());
        detalle.setProducto(d.getPrecioPorTipoUnidad().getProducto());
        detalle.setPrecioUnitario(d.getPrecioUnitario());
        detalle.setPrecioTotal(d.getPrecioUnitario() * d.getCantidad());
        return detalle;
    }
}
