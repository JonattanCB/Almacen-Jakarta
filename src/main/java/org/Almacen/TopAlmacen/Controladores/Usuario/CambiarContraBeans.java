package org.Almacen.TopAlmacen.Controladores.Usuario;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import lombok.Data;
import org.Almacen.TopAlmacen.DTO.Usuario.UsuarioDto;

import java.io.Serializable;

@Data
@Named("CambiarContraBeans")
@SessionScoped
public class CambiarContraBeans implements Serializable {

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


}
