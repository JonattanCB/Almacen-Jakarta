package org.Almacen.Siman.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "HistorialStock")
public class HistorialStock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stockUnidades", nullable = false)
    private StockUnidades stockUnidades;

    @Column(name = "CantidadStock", nullable = false)
    private double cantidadStock;

    @Column(name = "FechaRegistrada", nullable = false)
    private LocalDateTime fechaRegistrada = LocalDateTime.now();
}
