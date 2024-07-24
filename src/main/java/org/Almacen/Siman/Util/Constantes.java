package org.Almacen.Siman.Util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.Almacen.Siman.Model.TipoUnidad;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Constantes {
    public static final TipoUnidad UND = new TipoUnidad();
    static {
        UND.setId(1);
        UND.setAbrev("UND");
        UND.setNombre("Unidad");
    }
}