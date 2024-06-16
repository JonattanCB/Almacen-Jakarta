package org.Almacen.TopAlmacen.Controladores;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
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

@Named
@SessionScoped
@Data
public class BeansLogin implements Serializable {

    private List<Panels> lst;

    private MenuModel model;





    @PostConstruct
    public void init() {
        model = new DefaultMenuModel();
        DefaultSubMenu firstSubmenu = DefaultSubMenu.builder().label("wa").expanded(true).icon("pi pi-angle-right").build();
        DefaultMenuItem itemMenu = DefaultMenuItem.builder().value("pepe").build();
        firstSubmenu.getElements().add(itemMenu);


        itemMenu = DefaultMenuItem.builder().value("maria").build();
        firstSubmenu.getElements().add(itemMenu);
        DefaultSubMenu p = DefaultSubMenu.builder().label("we").expanded(true).build();

        model.getElements().add(firstSubmenu);
        model.getElements().add(p);
    }


    public  String initi2(){

        model = new DefaultMenuModel();
        DefaultSubMenu firstSubmenu = DefaultSubMenu.builder().label("wa").expanded(true).build();
            DefaultMenuItem itemMenu = DefaultMenuItem.builder().value("pepe").target("W").build();
            firstSubmenu.getElements().add(itemMenu);


            itemMenu = DefaultMenuItem.builder().value("maria").build();
            firstSubmenu.getElements().add(itemMenu);
        DefaultSubMenu p = DefaultSubMenu.builder().label("we").expanded(true).build();

            model.getElements().add(firstSubmenu);
            model.getElements().add(p);
        return "Gestion/Dashboard";
    }

}
