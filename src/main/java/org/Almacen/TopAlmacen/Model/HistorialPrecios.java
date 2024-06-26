package org.Almacen.TopAlmacen.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "HistorialPrecios")
public class HistorialPrecios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PrecioPorTipoUnidad", nullable = false)
    private PrecioPorTipoUnidad precioPorTipoUnidad;
    @Column(name = "precioRegistro", nullable = false)
    private Double precioRegistro;
    @Column(name = "FechaRegistro", nullable = false)
    private LocalTime fechaRegistro;
}
