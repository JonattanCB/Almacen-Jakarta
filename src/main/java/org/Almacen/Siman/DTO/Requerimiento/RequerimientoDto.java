package org.Almacen.Siman.DTO.Requerimiento;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.Almacen.Siman.Model.Dependencia;
import org.Almacen.Siman.Model.ItemsRequerimiento;
import org.Almacen.Siman.Model.Usuario;

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
    private Dependencia dependencia;
    private String estado;
    private String RazonEntrada;
    private String RazonSalida;
    private List<ItemsRequerimiento> requerimiento;
    private String aprobadoPor;
}
