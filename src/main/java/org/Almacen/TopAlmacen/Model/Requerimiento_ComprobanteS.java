package org.Almacen.TopAlmacen.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "Requerimiento_ComprobanteS")
public class Requerimiento_ComprobanteS {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JoinColumn(name = "requerimiento")
    @ManyToOne(fetch = FetchType.LAZY)
    private Requerimiento requerimiento;

    @JoinColumn(name = "comprobanteSalida")
    @ManyToOne(fetch = FetchType.LAZY)
    private ComprobanteSalida comprobanteSalida;
}
