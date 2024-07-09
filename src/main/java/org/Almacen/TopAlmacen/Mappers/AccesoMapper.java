package org.Almacen.TopAlmacen.Mappers;


import org.Almacen.TopAlmacen.DTO.Acceso.AccesoDto;
import org.Almacen.TopAlmacen.Model.Acceso;

public class AccesoMapper {

    public static AccesoDto toDto(Acceso acceso) {
        return new AccesoDto(acceso.getId(),acceso.getNombre(),acceso.getURL(),acceso.getIcon(),acceso.getTipo(),acceso.getSubMenuId());
    }
}
