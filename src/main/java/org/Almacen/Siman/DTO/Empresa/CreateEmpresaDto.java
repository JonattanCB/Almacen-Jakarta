package org.Almacen.Siman.DTO.Empresa;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.Almacen.Siman.Model.TipoEmpresa;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateEmpresaDto {
    private String NroRUC;
    private String nombre;
    private TipoEmpresa tipoEmpresa;
    private String direccion;
    private String estado;
}
