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
@Table(name = "Persona")
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "pNombre", nullable = false)
    private String pNombre;
    @Column(name = "sNombre", nullable = false)
    private String sNombre;
    @Column(name = "pApellido", nullable = false)
    private String pApellido;
    @Column(name = "sApellido", nullable = false)
    private String sApellido;
    @OneToOne(mappedBy = "persona")
    private Usuario usuario;
    @ManyToOne
    @JoinColumn(name = "TipoDocumento_ID", nullable = false)
    private TipoDocumento tipoDocumento;
    @Column(name = "nroDocumento", nullable = false)
    private String nroDocumento;
    @Column(name = "nroCelular")
    private String nroCelular;
    @Column(name = "estado")
    private String estado;
}
