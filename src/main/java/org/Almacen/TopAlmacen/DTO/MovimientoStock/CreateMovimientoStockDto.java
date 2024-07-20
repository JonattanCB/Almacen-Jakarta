package org.Almacen.TopAlmacen.DTO.MovimientoStock;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.Almacen.TopAlmacen.Model.Dependencia;
import org.Almacen.TopAlmacen.Model.PrecioPorTipoUnidad;
import org.Almacen.TopAlmacen.Model.TipoUnidad;
import org.Almacen.TopAlmacen.Model.Usuario;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateMovimientoStockDto {
    private String tipoMovimiento;
    private PrecioPorTipoUnidad precioPorTipoUnidad;
    private double cantidad;
    private TipoUnidad tipoUnidad;
    private Usuario solicitanteOResponsable;
    private Dependencia dependencia;
}
