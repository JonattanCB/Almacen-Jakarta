package org.Almacen.TopAlmacen.Controladores;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import lombok.Data;
import org.Almacen.TopAlmacen.DTO.Marca.MarcaDto;
import org.Almacen.TopAlmacen.DTO.Usuario.UsuarioDto;
import org.primefaces.util.LangUtils;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

@Data
@Named("UsuarioBeans")
@SessionScoped
public class UsuarioBeans implements Serializable {

    private List<UsuarioDto> usuarios;
    private List<UsuarioDto> usuariosSeleccioando;
    private UsuarioDto usuarioDto;

    @PostConstruct
    private void  init(){

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
        return (u.getId() >= filterInt && u.getId() <= filterInt);
    }

    private int getInteger(String string) {
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException ex) {
            return 0;
        }
    }

}
