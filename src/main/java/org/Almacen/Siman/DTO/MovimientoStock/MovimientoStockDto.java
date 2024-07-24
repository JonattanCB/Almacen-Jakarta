package org.Almacen.Siman.DTO.MovimientoStock;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.Almacen.Siman.Model.Dependencia;
import org.Almacen.Siman.Model.Producto;
import org.Almacen.Siman.Model.Usuario;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovimientoStockDto {
    private int id;
    private LocalDateTime fechaRegistro;
    private String tipoMovimiento;
    private Producto producto;
    private double cantidad;
    private Usuario solicitanteOResponsable;
    private Dependencia dependencia;

}
