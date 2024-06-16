package org.Almacen.TopAlmacen.DTO.Persona;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.Almacen.TopAlmacen.DTO.TipoDocumento.TipoDocumentoDto;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonaDto {
    private int id;
    private String pNombre;
    private String sNombre;
    private String pApellido;
    private String sApellido;
    private TipoDocumentoDto tipoDocumentoDto;
    private String nroDocumento;
    private String nroCelular;
    private String estado;
}
