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
@Table(name = "UnidadDependencia")
public class UnidadDependencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dependencia", nullable = false)
    private Dependencia dependencia;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "responsable")
    private Usuario responsable;
    @Column(name = "fechaRegistro",nullable = false)
    private LocalDate fechaRegistro=LocalDate.now();
    @OneToMany(mappedBy = "unidadDependencia")
    private List<Usuario> usuarios;
}
