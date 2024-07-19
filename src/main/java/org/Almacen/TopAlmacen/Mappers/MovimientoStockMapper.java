package org.Almacen.TopAlmacen.Mappers;

import org.Almacen.TopAlmacen.DTO.MovimientoStock.CreateMovimientoStockDto;
import org.Almacen.TopAlmacen.DTO.MovimientoStock.MovimientoStockDto;
import org.Almacen.TopAlmacen.Model.MovimientoStock;

public class MovimientoStockMapper {
    public static MovimientoStockDto toDto(MovimientoStock m) {
        var dto = new MovimientoStockDto();
        dto.setId(m.getId());
        dto.setFechaRegistro(m.getFechaRegistro());
        dto.setTipoMovimiento(m.getTipoMovimiento());
        dto.setProducto(m.getProducto());
        dto.setCantidad(m.getCantidad());
        dto.setTipoUnidad(m.getTipoUnidad());
        dto.setSolicitanteOResponsable(m.getSolicitante_Responsable());
        return dto;
    }

    public static MovimientoStock fromCrate(CreateMovimientoStockDto dto) {
        var mov = new MovimientoStock();
        mov.setTipoMovimiento(dto.getTipoMovimiento());
        mov.setCantidad(dto.getCantidad());
        mov.setTipoUnidad(dto.getTipoUnidad().getAbrev());
        return mov;
    }
}
