package org.Almacen.TopAlmacen.DTO.TipoEmpresa;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CreateTipoEmpresaDto {
    private String nombre;
    private String abrev;
}
