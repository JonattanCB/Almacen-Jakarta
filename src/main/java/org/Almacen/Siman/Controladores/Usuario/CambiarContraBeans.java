package org.Almacen.Siman.Controladores.Usuario;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import org.Almacen.Siman.DTO.Usuario.UsuarioDto;
import org.Almacen.Siman.Services.UsuarioService;
import org.Almacen.Siman.Util.PasswordUtil;
import org.primefaces.PrimeFaces;

import java.io.Serializable;

@Data
@Named("CambiarContraBeans")
@SessionScoped
public class CambiarContraBeans implements Serializable {

    @Inject
    private UsuarioService usuarioService;

    private String contra;

    private String  contra1;

    private String contra2;

    private UsuarioDto usuarioDto;

    @PostConstruct
    private void init(){
        usuarioDto = (UsuarioDto) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
    }

    public void abrirContrasenia(){
        contra = "";
        contra1 = "";
        contra2 = "";
    }

    public void Cambiar(){
        if(PasswordUtil.hashPassword(contra).equals(getUsuarioDto().getContra())){
            if (contra1.equals(contra2)) {
                usuarioService.cambiarContrasenia(contra1, usuarioDto.getId());
                abrirContrasenia();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("¡La contraseña fue actualizada!"));
                PrimeFaces.current().executeScript("PF('dialog-password').hide()");
            }else{
                FacesContext.getCurrentInstance().addMessage(null,  new FacesMessage(FacesMessage.SEVERITY_ERROR, "¡Error! Las nuevas contraseñas no son iguales.", null));
            }
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "¡Error! La contraseña actual es incorrecta.", null));
        }
        PrimeFaces.current().ajax().update("mensaje-plantilla");
    }

}
