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
@Table(name = "Usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "Correo", nullable = false)
    private String correo;
    @Column(name = "Contra", nullable = false)
    private String contra;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Rol", nullable = false)
    private Rol rol;

    @Column(name = "estado")
    private String estado;
    @Column(name = "FechaRegistro")
    private LocalDate fechaRegistro = LocalDate.now();
}
