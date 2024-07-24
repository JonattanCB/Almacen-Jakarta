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
    private LocalDateTime Fecha;
    private String Area;
    private String SolicitanteResponsable;
    private double invInicial;
    private double costoUni;
    private double stockEntrada;
    private double stockSalida;
    private double invFinal;
}
