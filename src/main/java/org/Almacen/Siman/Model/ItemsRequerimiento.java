package org.Almacen.Siman.Model;

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
@Table(name = "ItemsRequerimiento")
public class ItemsRequerimiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "requerimiento", nullable = false)
    private Requerimiento requerimiento;

    @Column(name = "Cantidad", nullable = false)
    private double Cantidad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipoUnidad", nullable = false)
    private TipoUnidad tipoUnidad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto", nullable = false)
    private Producto producto;

}
