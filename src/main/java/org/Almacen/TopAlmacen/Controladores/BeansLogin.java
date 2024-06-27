package org.Almacen.TopAlmacen.Controladores;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import lombok.Data;
import org.Almacen.TopAlmacen.Model.Panels;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named("BeansLogin")
@SessionScoped
@Data
public class BeansLogin implements Serializable {


    private MenuModel model;

    private String Nombre;

    private boolean abrirPerfil;

    private List<Panels> list;


    @PostConstruct
    public void init() {
        Nombre = "Jonattan Sebastian Contreras Baltazar";



        String contextPath = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
        model = new DefaultMenuModel();
        DefaultSubMenu firstSubmenu = DefaultSubMenu.builder().label("productos").expanded(true).build();
        DefaultMenuItem itemMenu = DefaultMenuItem.builder().value("marca").icon("pi pi-home").url(contextPath+"/protegido/Productos/marca.xhtml").update("contenido_escritorio-update").build();
        firstSubmenu.getElements().add(itemMenu);
        itemMenu = DefaultMenuItem.builder().value("categori").icon("pi pi-home").url(contextPath+"/protegido/Productos/categoria.xhtml").update("contenido_escritorio-update").build();
        firstSubmenu.getElements().add(itemMenu);
        model.getElements().add(firstSubmenu);
    }



}
