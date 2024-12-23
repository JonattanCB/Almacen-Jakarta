package org.Almacen.Siman.DTO.ComprobanteSalida;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PdfComprobanteSalidaDto {
    private LocalDate fecha;
    private String usuario;
    private String dependencia_s;
    private String para_uso;
    private String estado;
    private double totalC_v;
    private String aprobadoPor;

}
