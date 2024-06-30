package org.Almacen.TopAlmacen.Mappers;

import org.Almacen.TopAlmacen.DTO.Categoria.CategoriaDto;
import org.Almacen.TopAlmacen.DTO.Categoria.CreateCategoriaDto;
import org.Almacen.TopAlmacen.DTO.Categoria.UpdateCategoriaDto;
import org.Almacen.TopAlmacen.DTO.Rol.CreateRolDto;
import org.Almacen.TopAlmacen.DTO.Rol.RolDto;
import org.Almacen.TopAlmacen.DTO.Rol.UpdateRolDto;
import org.Almacen.TopAlmacen.Model.Categoria;
import org.Almacen.TopAlmacen.Model.Rol;

public class RolMapper {

    public static RolDto toDto(Rol rol) {
        return new RolDto(rol.getId() , rol.getNombre(), rol.getEstado(), rol.getFechaRegistro());
    }

    public static Rol toRol(Rol dto) {
        return new Rol(dto.getId(),dto.getNombre(),dto.getEstado(), dto.getFechaRegistro(),null);
    }

    public static Rol toRolFromCreate(CreateRolDto dto) {
        Rol r = new Rol();
        r.setNombre(dto.getNombre());
        r.setEstado(dto.getEstado());
        return r;
    }

    public static Rol torolFromUpdate(UpdateRolDto dto) {
        Rol r = new Rol();
        r.setNombre(dto.getNombre());
        r.setEstado(dto.getEstado());
        return r;
    }


}
