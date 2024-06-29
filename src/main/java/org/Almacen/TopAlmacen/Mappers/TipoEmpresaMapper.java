package org.Almacen.TopAlmacen.Mappers;

import org.Almacen.TopAlmacen.DTO.Categoria.CategoriaDto;
import org.Almacen.TopAlmacen.DTO.Categoria.CreateCategoriaDto;
import org.Almacen.TopAlmacen.DTO.Categoria.UpdateCategoriaDto;
import org.Almacen.TopAlmacen.DTO.TipoEmpresa.CreateTipoEmpresaDto;
import org.Almacen.TopAlmacen.DTO.TipoEmpresa.TipoEmpresaDto;
import org.Almacen.TopAlmacen.DTO.TipoEmpresa.UpdateTipoEmpresaDto;
import org.Almacen.TopAlmacen.Model.Categoria;
import org.Almacen.TopAlmacen.Model.TipoEmpresa;

public class TipoEmpresaMapper {

    public static TipoEmpresaDto toDto(TipoEmpresa te) {
        return new TipoEmpresaDto(te.getId(),te.getNombre(),te.getAbrev());
    }

    public static TipoEmpresa toTipoEmpresa(TipoEmpresaDto dto) {
        return new TipoEmpresa(dto.getId(), dto.getNombre(), dto.getAbrev());
    }

    public static TipoEmpresa toTipoEmpresaFromCreate(CreateTipoEmpresaDto dto) {
        TipoEmpresa td = new TipoEmpresa();
        td.setNombre(dto.getNombre());
        td.setAbrev(dto.getAbrev());
        return td;
    }

    public static TipoEmpresa toTipoEmpresaFromUpdate(UpdateTipoEmpresaDto dto) {
        TipoEmpresa td = new TipoEmpresa();
        td.setNombre(dto.getNombre());
        td.setAbrev(dto.getAbrev());
        return td;
    }

}
