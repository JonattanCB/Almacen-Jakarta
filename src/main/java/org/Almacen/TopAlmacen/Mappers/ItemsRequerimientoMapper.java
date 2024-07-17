package org.Almacen.TopAlmacen.Mappers;

import org.Almacen.TopAlmacen.DTO.ItemsRequerimiento.CreateItemsRequerimientoDto;
import org.Almacen.TopAlmacen.DTO.ItemsRequerimiento.ItemsRequerimientoDto;
import org.Almacen.TopAlmacen.DTO.ItemsRequerimiento.PdfItemsRequerimientosDto;
import org.Almacen.TopAlmacen.Model.ItemsRequerimiento;

public class ItemsRequerimientoMapper {
    public static ItemsRequerimientoDto toDto(ItemsRequerimiento item) {
        return new ItemsRequerimientoDto(item.getId(), item.getRequerimiento(), item.getCantidad(), item.getTipoUnidad(), item.getProducto(),null);
    }

    public static ItemsRequerimiento toEntity(ItemsRequerimientoDto dto) {
        return new ItemsRequerimiento(dto.getId(), dto.getRequerimiento(), dto.getCantidad(), dto.getTipoUnidad(), dto.getProducto());
    }

    public static PdfItemsRequerimientosDto toPdfDto(ItemsRequerimiento item) {
        return new PdfItemsRequerimientosDto(item.getId(), ProductoMapper.toConcatProduct(item.getProducto()), item.getTipoUnidad().getEstado(), item.getCantidad());
    }

    public static CreateItemsRequerimientoDto tocreate(ItemsRequerimientoDto item) {
        return new CreateItemsRequerimientoDto(item.getRequerimiento(), item.getCantidad(), item.getTipoUnidad(), item.getProducto());
    }

    public static ItemsRequerimiento fromCreate(CreateItemsRequerimientoDto dto) {
        var items = new ItemsRequerimiento();
        items.setRequerimiento(dto.getRequerimiento());
        items.setProducto(dto.getProducto());
        items.setCantidad(dto.getCantidad());
        items.setTipoUnidad(dto.getTipoUnidad());
        return items;
    }
}
