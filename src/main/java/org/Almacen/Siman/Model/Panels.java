package org.Almacen.Siman.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class    Panels {
    private int id;
    private String name;
    private String descripcio;
    private String estado;
    private int cantidad;
}
