package org.Almacen.TopAlmacen.Controladores;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import lombok.Data;
import org.Almacen.TopAlmacen.DTO.Dependencia.DependenciaDto;

import java.io.Serializable;

@Data
@Named("DependenciaBeans")
@SessionScoped
public class DependenciaBeans implements Serializable {

    private DependenciaDto dependenciaDto;

    @PostConstruct
    private void init(){

    }

    public void nuevaDependencia(){
        dependenciaDto = new DependenciaDto();
    }



}
