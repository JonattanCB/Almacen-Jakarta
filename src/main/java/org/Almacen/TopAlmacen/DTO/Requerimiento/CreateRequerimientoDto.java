package org.Almacen.TopAlmacen.DTO.Requerimiento;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.Almacen.TopAlmacen.Model.Usuario;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateRequerimientoDto {
    private String id;
    private Usuario usuario;
    private String RazonEntrada;
}
