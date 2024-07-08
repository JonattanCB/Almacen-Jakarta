package org.Almacen.TopAlmacen.Mappers;

import org.Almacen.TopAlmacen.DTO.StockUnidades.StockUnidadesDto;
import org.Almacen.TopAlmacen.DTO.StockUnidades.UpdateStockUnidadesDto;
import org.Almacen.TopAlmacen.Model.StockUnidades;

public class StockUnidadesMapper {
    public static StockUnidadesDto toStockUnidadesDto(StockUnidades stockUnidades) {
        return new StockUnidadesDto(stockUnidades.getId(), stockUnidades.getCantidadStockUnidad(), stockUnidades.getTipoUnidad());
    }

    public static StockUnidades toStockUnidadesFromUpdate(UpdateStockUnidadesDto dto) {
        StockUnidades s = new StockUnidades();
        s.setCantidadStockUnidad(dto.getCantidadStockUnidadesDto());
        return s;
    }
}
