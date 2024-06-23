package org.Almacen.TopAlmacen.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "ProductoProveedorEntrada")
public class ProductoProveedorEntrada {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int OC;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EmpresaId", nullable = false)
    private Empresa empresa;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario",nullable = false)
    private  Usuario usuario;
    @Column(name = "PrecioFinal",nullable = false)
    private double precioFinal;
    @OneToMany(mappedBy = "OC_id")
    private List<DetalleProductoProveedorEntrada> DetalleProductoProveedorEntrada;

}
