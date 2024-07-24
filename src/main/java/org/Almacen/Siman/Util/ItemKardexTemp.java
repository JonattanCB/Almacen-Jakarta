package org.Almacen.Siman.Util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemKardexTemp {
    private String fecha;
    private String area;
    private String solicitanteresponsable;
    private double invinicial;
    private double costouni;
    private double stockentrada;
    private double stocksalida;
    private double invfinal;
}
