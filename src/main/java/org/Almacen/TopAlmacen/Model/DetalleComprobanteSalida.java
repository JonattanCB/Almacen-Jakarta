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
@Table(name = "DetalleComprobanteSalida")
public class DetalleComprobanteSalida {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "ComprobanteSalida_ID", nullable = false)
    private ComprobanteSalida comprobanteSalida;
    @ManyToOne
    @JoinColumn(name = "Producto_ID", nullable = false)
    private Producto producto;
    @Column(name = "Cantidad", nullable = false)
    private double cantidad=0.00;
}
