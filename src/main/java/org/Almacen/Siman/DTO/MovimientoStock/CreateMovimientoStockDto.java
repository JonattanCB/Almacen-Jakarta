package org.Almacen.Siman.DTO.MovimientoStock;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.Almacen.Siman.Model.Dependencia;
import org.Almacen.Siman.Model.PrecioPorTipoUnidad;
import org.Almacen.Siman.Model.Usuario;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateMovimientoStockDto {
    private String tipoMovimiento;
    private PrecioPorTipoUnidad precioPorTipoUnidad;
    private double cantidad;
    private Usuario solicitanteOResponsable;
    private Dependencia dependencia;
}
