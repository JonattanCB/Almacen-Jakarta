package org.Almacen.TopAlmacen.Mappers;

import org.Almacen.TopAlmacen.DTO.Categoria.CategoriaDto;
import org.Almacen.TopAlmacen.DTO.Categoria.CreateCategoriaDto;
import org.Almacen.TopAlmacen.DTO.Categoria.UpdateCategoriaDto;
import org.Almacen.TopAlmacen.DTO.Rol.CreateRolDto;
import org.Almacen.TopAlmacen.DTO.Rol.RolConUsuariosDto;
import org.Almacen.TopAlmacen.DTO.Rol.RolDto;
import org.Almacen.TopAlmacen.DTO.Rol.UpdateRolDto;
import org.Almacen.TopAlmacen.Model.Categoria;
import org.Almacen.TopAlmacen.Model.Rol;

public class RolMapper {

    public static RolDto toRolDto(Rol rol) {
        return new RolDto(rol.getId() , rol.getNombre(), rol.getEstado(), rol.getFechaRegistro(), rol.getUnidadDependencia());
    }
    public static RolConUsuariosDto toRolConUsuariosDto(Rol rol) {
        return new RolConUsuariosDto(rol.getId(),rol.getNombre(),rol.getEstado(),rol.getFechaRegistro(),rol.getUsuarios(),rol.getUnidadDependencia());
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
