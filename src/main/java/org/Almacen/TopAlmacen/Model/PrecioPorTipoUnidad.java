package org.Almacen.TopAlmacen.Model;

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
@Table(name ="PrecioPorTipoUnidad")
public class PrecioPorTipoUnidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "TipoUnidad_ID", nullable = false)
    private TipoUnidad tipoUnidad;

    @ManyToOne
    @JoinColumn(name = "Producto_ID", nullable = false)
    private Producto producto;
    @Column(name = "PrecioUnitario",nullable = true)
    private double precioUnitario;
    @Column(name = "unidadesPorTipoUnidadDeProducto")
    private double unidadesPorTipoUnidadDeProducto;
    @OneToMany(mappedBy = "precioPorTipoUnidad")
    private List<HistorialPrecios> historialesDePrecios;

}
