package org.Almacen.TopAlmacen.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

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
    @Column(name = "TipoUnidad")
    private String tipoUnidad;
}
