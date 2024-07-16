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
        var detalleComprobanteSalida = new DetalleComprobanteSalida();
        d.setComprobanteSalida(detalleComprobanteSalida.getComprobanteSalida());
        d.setCantidad(detalleComprobanteSalida.getCantidad());
        d.setTipoUnidad(detalleComprobanteSalida.getTipoUnidad());
        d.setProducto(detalleComprobanteSalida.getProducto());
        d.setPrecioUnitario(detalleComprobanteSalida.getPrecioUnitario());
        d.setPrecioTotal(detalleComprobanteSalida.getPrecioUnitario() * d.getCantidad());
        return detalleComprobanteSalida;
    }
}
