package org.Almacen.TopAlmacen.DTO.MovimientoStock;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.Almacen.TopAlmacen.Model.Dependencia;
import org.Almacen.TopAlmacen.Model.Producto;
import org.Almacen.TopAlmacen.Model.Usuario;

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
    private String tipoUnidad;
    private Usuario solicitanteOResponsable;
    private Dependencia dependencia;

}
