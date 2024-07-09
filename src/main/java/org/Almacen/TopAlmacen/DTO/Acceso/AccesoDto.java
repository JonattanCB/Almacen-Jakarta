package org.Almacen.TopAlmacen.DTO.Acceso;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccesoDto {
    private int id;
    private String nombre;
    private String URL;
    private String Icon;
    private String Tipo;
    private int SubMenuId;
}
