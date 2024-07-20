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
@Table(name = "ComprobanteSalida")
public class ComprobanteSalida {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Solicitante", nullable = false)
    private Usuario usuario;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Dependencia", nullable = false)
    private Dependencia dependencia;
    @Column(name = "paraUso")
    private String paraUso;
    @Column(name = "fechaRegistro")
    private LocalDate fechaRegistro = LocalDate.now();
    @Column(name = "precioFinal", nullable = false)
    private double precioFinal;
    @Column(name = "Observacion")
    private String Observacion;
    @OneToMany(mappedBy = "comprobanteSalida")
    private List<DetalleComprobanteSalida> detalleComprobanteSalida;
    @Column(name = "Estado", nullable = false)
    private String estado;
}
