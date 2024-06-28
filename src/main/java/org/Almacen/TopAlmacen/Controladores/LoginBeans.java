package org.Almacen.TopAlmacen.Controladores;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import lombok.Data;
import org.Almacen.TopAlmacen.DTO.Usuario.UsuarioDto;

import java.io.Serializable;

@Data
@Named("LoginBeans")
@SessionScoped
public class LoginBeans implements Serializable {

    private UsuarioDto usuarioDto;

    @PostConstruct
    private  void init(){
        usuarioDto = new UsuarioDto();
    }

    public String iniciarSesion(){
        String redireccionar = null;
        try{
            usuarioDto.setNombres("Jonattan Sebastian");
            usuarioDto.setApellidos("Contreras Baltazar");
            if (usuarioDto.getCorreo().equals("1") && usuarioDto.getContra().equals("1")){
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", usuarioDto);
                redireccionar="protegido/principal?faces-redirect=true";
            }else{
                System.out.println("usuario incorrecto");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return redireccionar;
    }

    public void cerrarSession(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    }


}
