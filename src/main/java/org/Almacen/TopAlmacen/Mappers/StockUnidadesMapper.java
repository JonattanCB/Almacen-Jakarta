package org.Almacen.TopAlmacen.Mappers;

import org.Almacen.TopAlmacen.DTO.StockUnidades.StockUnidadesDto;
import org.Almacen.TopAlmacen.DTO.StockUnidades.TablaStockUnidadesDto;
import org.Almacen.TopAlmacen.DTO.StockUnidades.UpdateStockUnidadesDto;
import org.Almacen.TopAlmacen.Model.PrecioPorTipoUnidad;
import org.Almacen.TopAlmacen.Model.Producto;
import org.Almacen.TopAlmacen.Model.StockUnidades;

import java.util.List;

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
