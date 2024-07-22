package org.Almacen.TopAlmacen.Util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventoKardex {
    private LocalDateTime fecha;
    private String tipo;
    private Object evento;
}