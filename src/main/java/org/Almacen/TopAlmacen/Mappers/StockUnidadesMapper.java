package org.Almacen.TopAlmacen.Mappers;

import org.Almacen.TopAlmacen.DTO.StockUnidades.StockUnidadesDto;
import org.Almacen.TopAlmacen.DTO.StockUnidades.UpdateStockUnidadesDto;
import org.Almacen.TopAlmacen.Model.PrecioPorTipoUnidad;
import org.Almacen.TopAlmacen.Model.StockUnidades;

import java.util.List;
import java.util.stream.Collectors;

public class StockUnidadesMapper {
    public static StockUnidadesDto toStockUnidadesDto(StockUnidades stockUnidades) {
        return new StockUnidadesDto(stockUnidades.getId(), stockUnidades.getCantidadStockUnidad(), stockUnidades.getTipoUnidad());
    }

    public static StockUnidades toStockUnidadesFromUpdate(UpdateStockUnidadesDto dto) {
        StockUnidades s = new StockUnidades();
        s.setCantidadStockUnidad(dto.getCantidadStockUnidadesDto());
        return s;
    }

    public static String concatFromList(List<StockUnidades> list) {
        if (list == null || list.isEmpty()) {
            return "";
        }
        var fistStock = list.get(0);
        var getProduct = fistStock.getPrecios().get(0).getProducto();
        return ProductoMapper.toConcatProduct(getProduct);
    }
}
