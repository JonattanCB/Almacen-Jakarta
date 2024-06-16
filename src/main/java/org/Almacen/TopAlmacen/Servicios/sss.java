package org.Almacen.TopAlmacen.Servicios;


import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;

import java.io.Serializable;

@Stateless
@LocalBean
public class sss implements Serializable {

    @PersistenceContext(unitName = "TElectronica_jpa"  ///nombre del jpa
    private EntityManager em;


}
