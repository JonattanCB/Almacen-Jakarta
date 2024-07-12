package org.Almacen.TopAlmacen.Controladores.Login;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import org.Almacen.TopAlmacen.DTO.Usuario.UsuarioDto;
import org.Almacen.TopAlmacen.Model.Usuario;
import org.Almacen.TopAlmacen.Services.UsuarioService;
import org.primefaces.PrimeFaces;

import java.io.Serializable;

@Data
@Named("LoginBeans")
@SessionScoped
public class LoginBeans implements Serializable {

    private UsuarioDto usuarioDto;
    @Inject
    private UsuarioService usuarioService;

    @PostConstruct
    private void init() {
        usuarioDto = new UsuarioDto();
    }

    public String iniciarSesion() {
        String redireccionar = null;
        try {
            var findUser = usuarioService.checkUsuario(usuarioDto.getCorreo(), usuarioDto.getContra());
            if (findUser != null ) {
                usuarioDto = findUser;
                if(usuarioDto.getEstado().equals("INACTIVO")) {
                    limpiar();
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "¡Error! Correo está inactivo, comunícate con un administrador.", null));
                    PrimeFaces.current().ajax().update(":form:messages");
                }else{
                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", usuarioDto);
                    redireccionar = "protegido/principal?faces-redirect=true";
                }
            }else{
                limpiar();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "¡Error! Usuario y/o contraseña incorrectos.", null));
                PrimeFaces.current().ajax().update(":form:messages");
            }
        }catch(Exception e) {
        e.printStackTrace();
        }
        return redireccionar;
    }

    public void cerrarSession() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    }

    private void limpiar(){
        usuarioDto = new UsuarioDto();
    }



}
