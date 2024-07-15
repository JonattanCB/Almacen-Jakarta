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
@Table(name = "Producto")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "Nombre", nullable = false)
    private String nombre;

    @Column(name = "Color")
    private String color = "";

    @Column(name = "Peso")
    private String peso = "";

    @ManyToOne()
    @JoinColumn(name = "Marca_ID", nullable = false)
    private Marca marca;

    @ManyToOne
    @JoinColumn(name = "Categoria_ID", nullable = false)
    private Categoria categoria;

    @Column(name = "estado")
    private String estado;

    @Column(name = "FechaRegistro", nullable = false)
    private LocalDate fechaRegistro = LocalDate.now();

    @OneToOne(mappedBy = "producto", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private StockUnidades stockUnidades;
}
