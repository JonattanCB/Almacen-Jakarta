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
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    @Column(name = "Para_uso", nullable = false)
    private String paraUso;
    @Column(name = "Fecha_Entrega", nullable = false)
    private LocalDate fechaEntrega = LocalDate.now();
    @Column(name = "Estado_Aprobacion", nullable = false)
    private String estadoAprobacion;
    @Column(name = "Estado_Disponibilidad", nullable = false)
    private String estadoDisponibilidad;
    @Column(name = "Observacion", nullable = true)
    private String observacion;
    @Column(name = "Fecha_Registro", nullable = false)
    private LocalDate fechaRegistro= LocalDate.now();
    @OneToMany(mappedBy = "comprobanteSalida")
    private List<DetalleComprobanteSalida> detalleComprobanteSalida;
}
