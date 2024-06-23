package org.Almacen.TopAlmacen.DTO.ItemsRequerimiento;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.Almacen.TopAlmacen.DTO.Requerimiento.RequerimientoDto;
import org.Almacen.TopAlmacen.Model.TipoUnidad;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemsRequerimientoDto {
    private  int id;
    private RequerimientoDto requerimientoDto;
    private double Cantidad;
    private TipoUnidad tipoUnidad;
    private String descripcion;
}
