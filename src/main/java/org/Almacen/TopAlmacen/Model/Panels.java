package org.Almacen.TopAlmacen.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Panels {
    private int id;
    private String name;
    private String descripcio;
    private String estado;
    private int cantidad;
}
