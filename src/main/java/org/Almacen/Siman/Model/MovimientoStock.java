package org.Almacen.Siman.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "MovimientoStock")
public class MovimientoStock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "Fecha_Registro", nullable = false)
    private LocalDateTime fechaRegistro = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Producto_Id", nullable = false)
    private Producto producto;

    @Column(name = "Tipo_Movimiento", nullable = false)
    private String tipoMovimiento;

    @Column(name = "Cantidad", nullable = false)
    private double cantidad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Solicitante_Responsable", nullable = false)
    private Usuario solicitante_Responsable;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Dependencia_id", nullable = false)
    private Dependencia dependencia;
}
