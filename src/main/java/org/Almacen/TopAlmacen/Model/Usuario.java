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
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<ComprobanteSalida>comprobantes;
    @ManyToOne
    @JoinColumn(name = "Rol", nullable = false)
    private Rol rol;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Persona_ID",referencedColumnName = "id", nullable = false)
    private Persona persona;
    @ManyToOne
    @JoinColumn(name = "Dependencia_ID", nullable = false)
    private Dependencia dependencia;
    @Column(name = "Foto")
    private String foto;
    @Column(name = "estado")
    private String estado;
    @Column(name = "FechaRegistro")
    private LocalDate fechaRegistro = LocalDate.now();
}
