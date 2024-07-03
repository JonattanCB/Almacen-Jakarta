package org.Almacen.TopAlmacen.Mappers;

import org.Almacen.TopAlmacen.DTO.MovimientoStock.MovimientoStockDto;
import org.Almacen.TopAlmacen.Model.MovimientoStock;

public class MovimientoStockMapper {
    public static MovimientoStockDto toDto(MovimientoStock m) {
        return new MovimientoStockDto(m.getId(), m.getFechaRegistro(), m.getTipoMovimiento(), ProductoMapper.toConcatProduct(m.getPrecioPorTipoUnidad().getProducto()), m.getCantidad(), m.getTipoUnidad());
    }
}
