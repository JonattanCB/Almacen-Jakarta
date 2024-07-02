package org.Almacen.TopAlmacen.Mappers;

import org.Almacen.TopAlmacen.DTO.Producto.UpdateProductoDto;
import org.Almacen.TopAlmacen.DTO.StockUnidades.UpdateStockUnidadesDto;
import org.Almacen.TopAlmacen.Model.Producto;
import org.Almacen.TopAlmacen.Model.StockUnidades;

public class StockUnidadesMapper {
    public static StockUnidades toStockUnidadesFromUpdate(UpdateStockUnidadesDto dto) {
        StockUnidades s = new StockUnidades();
        s.setCantidadStockUnidad(dto.getCantidadStockUnidadesDto());
        return s;
    }
}
