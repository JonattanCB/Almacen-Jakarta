package org.Almacen.TopAlmacen.Model;

import jakarta.persistence.*;
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
@Entity
@Table(name = "StockUnidades")
public class StockUnidades {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "CantidadStockUnidad", nullable = false)
    private double cantidadStockUnidad;

    @Column(name = "TipoUnidad", nullable = false)
    private String tipoUnidad = "UND";

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Producto_ID", nullable = false)
    private Producto producto;
}
