package org.Almacen.Siman.DTO.HistorialPrecios;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.Almacen.Siman.Model.Usuario;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HistorialPreciosDto {
    private int id;
    private String precioPorTipoUnidad;
    private double precioRegistro;
    private LocalDateTime fechaRegistro;
    private Usuario responsable;
}
