package org.Almacen.Siman.Controladores.Login;

import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import org.Almacen.Siman.DTO.Usuario.UsuarioDto;

import java.io.IOException;
import java.io.Serializable;

@Named("SesionUsuarioBeans")
@ViewScoped
public class SesionUsuarioBeans implements Serializable {
    public void verificarSesion(){
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            UsuarioDto us = (UsuarioDto) context.getExternalContext().getSessionMap().get("usuario");
            if (us == null) {
                String contextPath = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
                context.getExternalContext().redirect(contextPath+"/errors/permisos.siman");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
