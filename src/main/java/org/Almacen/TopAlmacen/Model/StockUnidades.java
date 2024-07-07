package org.Almacen.TopAlmacen.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "StockUnidades")
public class StockUnidades {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "CantidadStockUnidad", nullable = false)
    private double CantidadStockUnidad;

    @Column(name = "TipoUnidad", nullable = false)
    private String tipoUnidad = "UND";

    @OneToMany(mappedBy = "stockUnidades",cascade = CascadeType.ALL)
    private List<PrecioPorTipoUnidad> precios;

}


