package org.Almacen.TopAlmacen.DTO.Empresa;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.Almacen.TopAlmacen.Model.TipoEmpresa;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateEmpresaDto {
    private String NroRUC;
    private String nombre;
    private TipoEmpresa tipoEmpresa;
    private String direccion;
}

