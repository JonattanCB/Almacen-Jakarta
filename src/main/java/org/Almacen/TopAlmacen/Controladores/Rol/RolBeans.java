package org.Almacen.TopAlmacen.Controladores.Rol;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import org.Almacen.TopAlmacen.DTO.Categoria.CategoriaDto;
import org.Almacen.TopAlmacen.DTO.Rol.RolDto;
import org.Almacen.TopAlmacen.Services.RolService;
import org.primefaces.util.LangUtils;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

@Data
@Named("RolBeans")
@ViewScoped
public class RolBeans implements Serializable {

    @Inject
    private RolService rolService;

    private RolDto rolDto;

    private int idRol;

    private List<RolDto> rolDtoList;

    private List<RolDto> rolDtoListSelect;

    @PostConstruct
    private void init(){
        loadRoles();
    }


    private  void loadRoles(){
        try {
            rolDtoList = rolService.getAllRol();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void NuevoRoles(){
        rolDto = new RolDto();
    }

    public void cargarRolEdiccion(){

    }

    public  void cambiarEstado(){

    }

    public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
        if (LangUtils.isValueBlank(filterText)) {
            return true;
        }
        int filterInt = getInteger(filterText);
        RolDto c = (RolDto) value;
        return (c.getId() >= filterInt && c.getId() <= filterInt)
                || c.getNombre().toLowerCase().contains(filterText)
                || c.getEstado().toLowerCase().contains(filterText);
    }

    private int getInteger(String string) {
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException ex) {
            return 0;
        }
    }



}
