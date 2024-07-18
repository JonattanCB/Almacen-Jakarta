package org.Almacen.TopAlmacen.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "Requerimiento")
public class Requerimiento {
    @Id
    private String id;
    @Column(name = "fechaRegistrada", nullable = false)
    private LocalDateTime fechaRegistrada = LocalDateTime.now();
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unidadDependencia", nullable = false)
    private UnidadDependencia unidadDependencia;
    @Column(name = "Estado", nullable = false)
    private String Estado;
    @Column(name = "RazonEntrada")
    private String RazonEntrada;
    @Column(name = "RazonSalida")
    private String RazonSalida;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "requerimiento", cascade = CascadeType.ALL)
    private List<ItemsRequerimiento> Requerimientos;

}
