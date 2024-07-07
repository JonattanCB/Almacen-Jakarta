package org.Almacen.TopAlmacen.Mappers;

import org.Almacen.TopAlmacen.DTO.ItemsRequerimiento.CreateItemsRequerimientoDto;
import org.Almacen.TopAlmacen.DTO.ItemsRequerimiento.ItemsRequerimientoDto;
import org.Almacen.TopAlmacen.DTO.Requerimiento.CreateRequerimientoDto;
import org.Almacen.TopAlmacen.Model.ItemsRequerimiento;
import org.Almacen.TopAlmacen.Model.Requerimiento;

public class ItemsRequerimientoMapper {
    public static ItemsRequerimientoDto toDto(ItemsRequerimiento item) {
        return new ItemsRequerimientoDto(item.getId(), item.getRequerimiento(), item.getCantidad(), item.getTipoUnidad(), item.getDescripcion());
    }

    public static ItemsRequerimiento toEntity(ItemsRequerimientoDto dto) {
        return new ItemsRequerimiento(dto.getId(), dto.getRequerimiento(), dto.getCantidad(), dto.getTipoUnidad(), dto.getDescripcion());
    }

    public static ItemsRequerimiento fromCreate(CreateItemsRequerimientoDto dto) {
        var items = new ItemsRequerimiento();
        items.setRequerimiento(items.getRequerimiento());
        items.setDescripcion(items.getDescripcion());
        items.setCantidad(items.getCantidad());
        items.setTipoUnidad(items.getTipoUnidad());
        return items;
    }
}
