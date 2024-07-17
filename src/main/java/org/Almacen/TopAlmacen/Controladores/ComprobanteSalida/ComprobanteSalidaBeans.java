package org.Almacen.TopAlmacen.Controladores.ComprobanteSalida;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import lombok.Data;

import java.io.Serializable;

@Data
@Named("ComprobanteSalidaBeans")
@ViewScoped
public class ComprobanteSalidaBeans implements Serializable {



    @PostConstruct
    private void init(){

    }

    public void registrarComprobateSalida(){

    }


}
