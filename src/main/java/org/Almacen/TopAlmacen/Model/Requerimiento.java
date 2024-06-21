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
@Table(name = "Requerimiento")
public class Requerimiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "fechaRegistrada", nullable = false)
    private LocalDate fechaRegistrada;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unidadDependencia", nullable = false)
    private UnidadDependencia unidadDependencia;
    @Column(name = "Estado", nullable = false)
    private String Estado;
    @Column(name = "Razon", nullable = false)
    private String Razon;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "requerimiento")
    private List<ItemsRequerimiento>Requerimientos;


}
