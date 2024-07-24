package org.Almacen.Siman.Util;

import lombok.Getter;
import lombok.Setter;
import org.Almacen.Siman.Model.TipoUnidad;

@Getter
@Setter
public class Constantes {
    public static final TipoUnidad UND = new TipoUnidad();
    static {
        UND.setId(1);
        UND.setAbrev("UND");
        UND.setNombre("Unidad");
    }
}