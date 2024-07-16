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
@Table(name = "DetalleProductoProveedorEntrada")
public class DetalleProductoProveedorEntrada {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OC_id")
    private ProductoProveedorEntrada OC_id;
    @Column(name = "Cantidad", nullable = false)
    private double cantidad;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TipoUnidad_ID")
    private TipoUnidad tipoUnidad;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto", nullable = false)
    private Producto producto;
    @Column(name = "PrecioUnitario", nullable = false)
    private double precioUnitario;
    @Column(name = "precioTotal", nullable = false)
    private double precioTotal;
}
