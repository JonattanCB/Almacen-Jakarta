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
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "Marca")
public class Marca {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "Nombre", nullable = false)
    private String nombre;
    @Column(name = "Descripcion")
    private String descripcion;
    @Column(name = "FechaRegistro")
    private LocalDate fechaRegistro=LocalDate.now();
    @Column(name = "estado", nullable = false)
    private String estado;
    @OneToMany(mappedBy = "marca")
    private List<Producto> productos;
}
