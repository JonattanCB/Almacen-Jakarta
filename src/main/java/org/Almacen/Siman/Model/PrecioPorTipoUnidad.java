package org.Almacen.Siman.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PrecioPorTipoUnidad")
public class PrecioPorTipoUnidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TipoUnidad_ID", nullable = false)
    private TipoUnidad tipoUnidad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Producto_ID", nullable = false)
    private Producto producto;

    @Column(name = "PrecioUnitario", nullable = false)
    private double precioUnitario;

    @Column(name = "unidadesPorTipoUnidadDeProducto")
    private double unidadesPorTipoUnidadDeProducto;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "precioPorTipoUnidad")
    private List<HistorialPrecios> historialPrecios;

}
