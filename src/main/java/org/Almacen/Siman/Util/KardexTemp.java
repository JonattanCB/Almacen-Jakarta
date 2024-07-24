package org.Almacen.Siman.Util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class KardexTemp {
    private String producto;
    private double invInicial;
    private String undMedida = "UND";
    private String Periodo;
    private List<ItemKardexTemp> items = new ArrayList<>();
}
