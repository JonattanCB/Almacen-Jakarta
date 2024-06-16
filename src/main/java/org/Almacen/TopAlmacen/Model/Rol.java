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
@Table(name = "Rol")
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "Nombre", nullable = false)
    private String nombre;
    @Column(name = "Descripcion", nullable = false)
    private String descripcion;
    @Column(name = "Estado", nullable = false)
    private String estado;
    @Column(name = "FechaRegistro", nullable = false)
    private LocalDate fechaRegistro;
    @OneToMany(mappedBy = "Usuarios")
    private List<Usuario> usuarios;
}
