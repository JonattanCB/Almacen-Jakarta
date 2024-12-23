package org.Almacen.Siman.Model;

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
@Table(name = "Rol")
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "Nombre", nullable = false)
    private String nombre;
    @Column(name = "Estado", nullable = false)
    private String estado;
    @Column(name = "FechaRegistro", nullable = false)
    private LocalDate fechaRegistro = LocalDate.now();
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private UnidadDependencia unidadDependencia;
    @OneToMany(mappedBy = "rol", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Acceso> accesos;
}
