package org.Almacen.TopAlmacen.Mappers;

import org.Almacen.TopAlmacen.DTO.MovimientoStock.CreateMovimientoStockDto;
import org.Almacen.TopAlmacen.DTO.MovimientoStock.MovimientoStockDto;
import org.Almacen.TopAlmacen.Model.MovimientoStock;

public class MovimientoStockMapper {
    public static MovimientoStockDto toDto(MovimientoStock m) {
        return new MovimientoStockDto(m.getId(), m.getFechaRegistro(), m.getTipoMovimiento(), ProductoMapper.toConcatProduct(m.getPrecioPorTipoUnidad().getProducto()), m.getCantidad(), m.getTipoUnidad());
    }

    public static MovimientoStock fromCrate(CreateMovimientoStockDto dto) {
        var mov = new MovimientoStock();
        mov.setTipoMovimiento(dto.getTipoMovimiento());
        mov.setDescripcion(ProductoMapper.toConcatProduct(dto.getPrecioPorTipoUnidad().getProducto()));
        mov.setCantidad(dto.getCantidad());
        mov.setTipoUnidad(dto.getTipoUnidad().getAbrev());
        System.out.println(mov.getTipoMovimiento() + mov.getDescripcion() + mov.getCantidad() + mov.getTipoUnidad());
        return mov;
    }
}
