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

    @Column(name = "Cantidad", nullable = false)
    private double cantidad;
    @ManyToOne
    @JoinColumn(name = "TipoUnidad",nullable = false)
    private TipoUnidad tipoUnidad;

    @Column(name = "Descripcion",nullable = false)
    private String descripcion;

    @Column(name = "PrecioUnitario",nullable = false)
    private double precioUnitario;

    @Column(name = "PrecioTotal",nullable = false)
    private double precioTotal;
}
