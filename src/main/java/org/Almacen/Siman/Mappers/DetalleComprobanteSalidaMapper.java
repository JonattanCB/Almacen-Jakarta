package org.Almacen.Siman.Mappers;

import org.Almacen.Siman.DTO.DetalleComprobanteSalida.CreateDetalleComprobanteSalidaDto;
import org.Almacen.Siman.DTO.DetalleComprobanteSalida.DetalleComprobanteSalidaDto;
import org.Almacen.Siman.Model.DetalleComprobanteSalida;


public class DetalleComprobanteSalidaMapper {
    public static DetalleComprobanteSalidaDto toDto(DetalleComprobanteSalida d) {
        return new DetalleComprobanteSalidaDto(d.getId(), d.getComprobanteSalida(), d.getCantidad(), d.getTipoUnidad(), d.getProducto(), null,ProductoMapper.toConcatProduct(d.getProducto()),d.getPrecioUnitario(),d.getPrecioTotal() );
    }

    public static CreateDetalleComprobanteSalidaDto toCreateDto(DetalleComprobanteSalidaDto d) {
        return new CreateDetalleComprobanteSalidaDto(d.getComprobanteSalida(), d.getCantidad(),d.getPrecioPorTipoUnidad(),d.getPrecioUnitario(),d.getPrecioTotal());
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
