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
@Table(name = "Acceso")
public class Acceso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Rol_ID")
    private Rol rol;
    @Column(name = "URL")
    private String URL;
    @Column(name = "Nombre")
    private String Nombre;
    @Column(name = "Icon")
    private String Icon;
    @Column(name = "Tipo")
    private String Tipo;
    @Column(name = "SubMenuId")
    private int SubMenuId;

}
