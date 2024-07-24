package org.Almacen.Siman.DTO.ItemsRequerimiento;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.Almacen.Siman.Model.Producto;
import org.Almacen.Siman.Model.Requerimiento;
import org.Almacen.Siman.Model.TipoUnidad;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemsRequerimientoDto {
    private int id;
    private Requerimiento requerimiento;
    private double Cantidad;
    private TipoUnidad tipoUnidad;
    private Producto producto;
    private String descripcionProducto;
}
