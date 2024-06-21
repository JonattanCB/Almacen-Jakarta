package org.Almacen.TopAlmacen.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
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

    @ManyToOne
    @JoinColumn(name = "PrecioPorTipoUnidad_id",nullable = false)
    private PrecioPorTipoUnidad precioPorTipoUnidad;
    @Column(name = "Fecha_Registro",nullable = false)
    private LocalDate fechaRegistro;
    @Column(name = "Tipo_Movimiento",nullable = false)
    private String tipoMovimiento;
}
