package org.Almacen.TopAlmacen.DTO.ItemsRequerimiento;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.Almacen.TopAlmacen.Model.Producto;
import org.Almacen.TopAlmacen.Model.Requerimiento;
import org.Almacen.TopAlmacen.Model.TipoUnidad;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CreateItemsRequerimientoDto {
    private Requerimiento requerimiento;
    private double Cantidad;
    private TipoUnidad tipoUnidad;
    private Producto producto;
}
