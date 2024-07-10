package org.Almacen.TopAlmacen.Mappers;

import org.Almacen.TopAlmacen.DTO.ItemsRequerimiento.CreateItemsRequerimientoDto;
import org.Almacen.TopAlmacen.DTO.ItemsRequerimiento.ItemsRequerimientoDto;
import org.Almacen.TopAlmacen.Model.ItemsRequerimiento;

public class ItemsRequerimientoMapper {
    public static ItemsRequerimientoDto toDto(ItemsRequerimiento item) {
        return new ItemsRequerimientoDto(item.getId(), item.getRequerimiento(), item.getCantidad(), item.getTipoUnidad(), item.getDescripcion());
    }

    public static ItemsRequerimiento toEntity(ItemsRequerimientoDto dto) {
        return new ItemsRequerimiento(dto.getId(), dto.getRequerimiento(), dto.getCantidad(), dto.getTipoUnidad(), dto.getDescripcion());
    }

    public static ItemsRequerimiento fromCreate(CreateItemsRequerimientoDto dto) {
        var items = new ItemsRequerimiento();
        items.setRequerimiento(dto.getRequerimiento());
        items.setDescripcion(dto.getDescripcion());
        items.setCantidad(dto.getCantidad());
        items.setTipoUnidad(dto.getTipoUnidad());
        return items;
    }
}
