package org.Almacen.TopAlmacen.Controladores.Login;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import org.Almacen.TopAlmacen.DTO.Acceso.AccesoDto;
import org.Almacen.TopAlmacen.DTO.Usuario.UsuarioDto;
import org.Almacen.TopAlmacen.Model.UnidadDependencia;
import org.Almacen.TopAlmacen.Services.AccesoService;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

import java.awt.*;
import java.io.Serializable;
import java.util.List;

@Data
@Named("MenuBeans")
@SessionScoped
public class MenuBeans implements Serializable {

    @Inject
    private AccesoService accesoService;

    private UsuarioDto usuarioDto;

    private MenuModel model;

    private List<AccesoDto> listaAcceso;

    @PostConstruct
    private void init(){
        usuarioDto = (UsuarioDto) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
      //  loadLista();
       // menuListar();
    }

    private void loadLista(){
        try {
            listaAcceso = accesoService.listarAccesos(usuarioDto.getUnidad().getRol().getId());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void menuListar(){
        model = new DefaultMenuModel();
        for (AccesoDto m :listaAcceso){
            if (m.getTipo().toLowerCase().equals("S")){
                DefaultSubMenu subMenu = DefaultSubMenu.builder().label(m.getNombre()).expanded(true).build();
                for (AccesoDto m2 : listaAcceso){
                    if(m2.getSubMenuId() != 0){
                        if(m2.getSubMenuId() == m.getId()){
                            DefaultMenuItem menuItem = DefaultMenuItem.builder().value(m2.getNombre()).build();
                            subMenu.getElements().add(menuItem);
                        }
                    }
                }
                model.getElements().add(subMenu);
            }
        }

    }



}
