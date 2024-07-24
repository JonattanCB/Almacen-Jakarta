package org.Almacen.Siman.Mappers;

import org.Almacen.Siman.DTO.Rol.CreateRolDto;
import org.Almacen.Siman.DTO.Rol.RolDto;
import org.Almacen.Siman.DTO.Rol.UpdateRolDto;
import org.Almacen.Siman.Model.Rol;

public class RolMapper {

    public static RolDto toRolDto(Rol rol) {
        return new RolDto(rol.getId(), rol.getNombre(), rol.getEstado(), rol.getFechaRegistro(), rol.getUnidadDependencia());
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
