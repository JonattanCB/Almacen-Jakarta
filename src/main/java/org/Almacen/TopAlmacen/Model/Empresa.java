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
@Table(name = "Empresa")
public class Empresa {
    @Id
    private String NroRUC;
    @Column(name = "Nombre", nullable = false)
    private String nombre;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipoEmpresa_Id")
    private TipoEmpresa tipoEmpresa;
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "estado")
    private String estado;
}
