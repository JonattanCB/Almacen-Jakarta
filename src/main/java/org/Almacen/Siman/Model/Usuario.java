package org.Almacen.Siman.Model;

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
@Table(name = "Usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "Correo", nullable = false)
    private String correo;
    @Column(name = "Contra", nullable = false)
    private String contra;
    @Column(name = "Nombres", nullable = false)
    private String nombres;
    @Column(name = "Apellidos", nullable = false)
    private String apellidos;
    @Column(name = "estado")
    private String estado;
    @Column(name = "FechaRegistro")
    private LocalDate fechaRegistro = LocalDate.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unidadDependencia", nullable = false)
    private UnidadDependencia unidadDependencia;
}
