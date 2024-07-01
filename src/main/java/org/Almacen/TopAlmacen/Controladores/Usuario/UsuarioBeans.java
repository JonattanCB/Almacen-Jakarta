package org.Almacen.TopAlmacen.Controladores.Usuario;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import org.Almacen.TopAlmacen.DTO.Usuario.UsuarioDto;
import org.Almacen.TopAlmacen.Services.UsuarioService;
import org.primefaces.util.LangUtils;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

@Data
@Named("UsuarioBeans")
@ViewScoped
public class UsuarioBeans implements Serializable {

    @Inject
    private UsuarioService usuarioService;

    private UsuarioDto usuarioDto;

    private int idUsuario;

    private List<UsuarioDto> usuarios;

    private List<UsuarioDto> usuariosSeleccioando;


    @PostConstruct
    private void  init(){
        loadUsuario();
    }

    private void  loadUsuario(){
        try{
            usuarios = usuarioService.getAllUsuario();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void NuevoUsuario(){
        usuarioDto = new UsuarioDto();
    }

    public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
        if (LangUtils.isValueBlank(filterText)) {
            return true;
        }
        int filterInt = getInteger(filterText);
        UsuarioDto u = (UsuarioDto) value;
        return (u.getId() >= filterInt && u.getId() <= filterInt)
                ||(u.getNombres() +" "+u.getApellidos()).toLowerCase().contains(filterText)
                ||u.getRol().getNombre().toLowerCase().contains(filterText)
                || u.getEstado().toLowerCase().contains(filterText);
    }

    private int getInteger(String string) {
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException ex) {
            return 0;
        }
    }

}
