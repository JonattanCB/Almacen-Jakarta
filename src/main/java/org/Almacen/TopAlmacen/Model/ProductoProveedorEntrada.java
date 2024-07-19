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
@Table(name = "ProductoProveedorEntrada")
public class ProductoProveedorEntrada {
    @Id
    private String OC;
    @Column(name = "FechaRegistro", nullable = false)
    private LocalDateTime FechaRegistro = LocalDateTime.now();
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EmpresaId", nullable = false)
    private Empresa empresa;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario", nullable = false)
    private Usuario usuario;
    @Column(name = "PrecioFinal", nullable = false)
    private double precioFinal = 0.00;
    @OneToMany(mappedBy = "OC_id", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<DetalleProductoProveedorEntrada> DetalleProductoProveedorEntrada;
    @Column(name = "Observacion")
    private String Observacion;
    @Column(name = "Estado")
    private String estado;
}
