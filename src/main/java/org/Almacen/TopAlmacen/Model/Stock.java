package org.Almacen.TopAlmacen.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "Stock")
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne
    @JoinColumn(name = "StockID", nullable = false)
    private Producto producto;
    @Column(name = "Cantidad", nullable = false)
    private double cantidad=0.00;
    @Column(name = "FechaRegistro", nullable = false)
    private LocalDate fechaRegistro=LocalDate.now();
}
