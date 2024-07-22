package org.Almacen.TopAlmacen.DTO.DetalleComprobanteSalida;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PdfDetallesComprobanteSalidaDto {
    private int item_s;
    private double cantidad_s;
    private String medida_s;
    private String descripcion_s;
    private String codigo_d;
    private double cant_d;
    private String medida_d;
    private double unidad_v;
    private double total_v;
}
