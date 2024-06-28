package org.Almacen.TopAlmacen.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "StockUnidades")
public class StockUnidades {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PrecioPorTipoUnidad", nullable = false)
    private PrecioPorTipoUnidad precioPorTipoUnidad;

    @Column(name = "CantidadStockUnidad")
    private double CantidadStockUnidad;
    @Column(name = "TipoUnidad", nullable = false)
    private String tipoUnidad;
}
