package org.Almacen.TopAlmacen.Mappers;

import org.Almacen.TopAlmacen.DTO.TipoUnidad.CreateTipoUnidadDto;
import org.Almacen.TopAlmacen.DTO.TipoUnidad.TipoUnidadDto;
import org.Almacen.TopAlmacen.DTO.TipoUnidad.UpdateTipoUnidadDto;
import org.Almacen.TopAlmacen.Model.TipoUnidad;

public class TipoUnidadMapper {

    public static TipoUnidadDto toDto(TipoUnidad tu) {
        return new TipoUnidadDto(tu.getId(),tu.getNombre(),tu.getAbrev());
    }

    public static TipoUnidad toTipoUnidad(TipoUnidadDto dto) {
        return new TipoUnidad(dto.getId(),dto.getNombre(),dto.getAbrev(), null,null);
    }

    public static TipoUnidad toTipoUnidadFromCreate(CreateTipoUnidadDto dto) {
        TipoUnidad tu = new TipoUnidad();
        tu.setNombre(dto.getNombre());
        tu.setAbrev(dto.getAbrev());
        return tu;
    }

    public static TipoUnidad toTipoUnidadFromUpdate(UpdateTipoUnidadDto dto) {
        TipoUnidad tu = new TipoUnidad();
        tu.setNombre(dto.getNombre());
        tu.setAbrev(dto.getAbrev());
        return tu;
    }
}
