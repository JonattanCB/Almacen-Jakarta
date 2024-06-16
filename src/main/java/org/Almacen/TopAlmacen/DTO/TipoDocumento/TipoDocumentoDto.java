package org.Almacen.TopAlmacen.DTO.TipoDocumento;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TipoDocumentoDto {
    private int id;
    private String nombre;
    private String descripcion;
}
