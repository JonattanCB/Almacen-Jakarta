package org.Almacen.Siman.DTO.Requerimiento;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.Almacen.Siman.Model.Dependencia;
import org.Almacen.Siman.Model.Usuario;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateRequerimientoDto {
    private String id;
    private Usuario usuario;
    private String RazonEntrada;
    private Dependencia dependencia;
}
