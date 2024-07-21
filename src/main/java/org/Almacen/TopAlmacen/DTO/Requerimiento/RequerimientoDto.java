package org.Almacen.TopAlmacen.DTO.Requerimiento;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.Almacen.TopAlmacen.Model.ItemsRequerimiento;
import org.Almacen.TopAlmacen.Model.UnidadDependencia;
import org.Almacen.TopAlmacen.Model.Usuario;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequerimientoDto {
    private String id;
    private LocalDateTime fechaRegistrada;
    private Usuario usuario;
    private String estado;
    private String RazonEntrada;
    private String RazonSalida;
    private List<ItemsRequerimiento> requerimiento;
}
