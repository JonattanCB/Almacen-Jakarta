package org.Almacen.TopAlmacen.Controladores.Usuario;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import org.Almacen.TopAlmacen.DTO.Usuario.UsuarioDto;
import org.Almacen.TopAlmacen.Services.UsuarioService;
import org.Almacen.TopAlmacen.Util.PasswordUtil;

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
                System.out.println("Cambiado Contrasenia");
            }else{
                System.out.println("contra 1 y contra 2 no son iguales");
            }
        }else{
            System.out.println("Password Incorrecta");
        }
    }

}
