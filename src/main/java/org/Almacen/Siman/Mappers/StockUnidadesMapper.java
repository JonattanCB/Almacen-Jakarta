package org.Almacen.Siman.Mappers;

import org.Almacen.Siman.DTO.StockUnidades.StockUnidadesDto;
import org.Almacen.Siman.DTO.StockUnidades.TablaStockUnidadesDto;
import org.Almacen.Siman.DTO.StockUnidades.UpdateStockUnidadesDto;
import org.Almacen.Siman.Model.StockUnidades;

public class StockUnidadesMapper {
    public static StockUnidadesDto toStockUnidadesDto(StockUnidades stockUnidades) {
        return new StockUnidadesDto(stockUnidades.getId(), stockUnidades.getCantidadStockUnidad(), stockUnidades.getTipoUnidad());
    }

    public static TablaStockUnidadesDto toTablaStockUnidadesDto(StockUnidades s) {
        return new TablaStockUnidadesDto(s.getId(), ProductoMapper.toConcatProduct(s.getProducto()), s.getCantidadStockUnidad(), s.getTipoUnidad());
    }

    public static StockUnidades toStockUnidadesFromUpdate(UpdateStockUnidadesDto dto) {
        StockUnidades s = new StockUnidades();
        s.setCantidadStockUnidad(dto.getCantidadStockUnidadesDto());
        return s;
    }

}
