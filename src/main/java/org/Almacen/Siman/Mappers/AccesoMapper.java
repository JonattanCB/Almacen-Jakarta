package org.Almacen.Siman.Mappers;


import org.Almacen.Siman.DTO.Acceso.AccesoDto;
import org.Almacen.Siman.Model.Acceso;

public class AccesoMapper {

    public static AccesoDto toDto(Acceso acceso) {
        return new AccesoDto(acceso.getId(),acceso.getNombre(),acceso.getURL(),acceso.getIcon(),acceso.getTipo(),acceso.getSubMenuId());
    }
}
