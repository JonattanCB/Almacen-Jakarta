package org.Almacen.TopAlmacen.DTO.ItemsRequerimiento;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PdfItemsRequerimientosDto {
    private int id_p;
    private String desc_p;
    private String nom_tunid;
    private double cantidad;
}
