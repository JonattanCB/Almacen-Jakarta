package org.Almacen.TopAlmacen.Controladores.Login;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import lombok.Data;
import org.Almacen.TopAlmacen.DTO.Usuario.UsuarioDto;
import org.Almacen.TopAlmacen.Model.UnidadDependencia;

import java.io.Serializable;

@Data
@Named("MenuBeans")
@SessionScoped
public class MenuBeans implements Serializable {

    private UsuarioDto usuarioDto;

    @PostConstruct
    private void init(){
        usuarioDto = (UsuarioDto) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");

    }




}
