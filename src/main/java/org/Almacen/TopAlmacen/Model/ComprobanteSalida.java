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
@Table(name ="ComprobanteSalida")
public class ComprobanteSalida {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unidadDependencia", nullable = false)
    private  UnidadDependencia unidadDependencia;
    @Column(name = "paraUso")
    private String paraUso;
    @Column(name = "fechaRegistro")
    private LocalDate fechaRegistro;
    @Column(name = "precioFinal")
    private double precioFinal;
    @OneToMany(mappedBy = "comprobanteSalida")
    private List<DetalleComprobanteSalida> detalleComprobanteSalida;
}
