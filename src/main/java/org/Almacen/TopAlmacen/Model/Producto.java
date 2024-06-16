package org.Almacen.TopAlmacen.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "Producto")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "Nombre", nullable = false)
    private String nombre;
    @Column(name = "Marca", nullable = false)
    private String marca;
    @ManyToOne
    @JoinColumn(name = "Categoria_ID", nullable = false)
    private Categoria categoria;
    @ManyToOne
    @JoinColumn(name = "TipoUnidad_ID", nullable = false)
    private TipoUnidad tipoUnidad;
    @Column(name = "Estado", nullable = false)
    private String estado;
    @Column(name = "FechaRegistro", nullable = false)
    private LocalDate FechaRegistro = LocalDate.now();
    @OneToOne(mappedBy = "producto", cascade = CascadeType.ALL)
    private Stock stock;
    @OneToMany(mappedBy = "producto")
    private List<DetalleComprobanteSalida> detalleComprobanteSalida;

}
