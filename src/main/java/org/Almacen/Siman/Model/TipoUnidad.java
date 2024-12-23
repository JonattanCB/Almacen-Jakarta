package org.Almacen.Siman.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "TipoUnidad")
public class TipoUnidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "Nombre", nullable = false)
    private String nombre;
    @Column(name = "Abrev", nullable = false)
    private String Abrev;
    @Column(name = "Estado", nullable = false)
    private String estado;
    @Column(name = "FechaRegistro", nullable = false)
    private LocalDate FechaRegistro=LocalDate.now();
}
