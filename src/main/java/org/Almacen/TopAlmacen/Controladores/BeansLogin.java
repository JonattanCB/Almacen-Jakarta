package org.Almacen.TopAlmacen.Controladores;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import lombok.Data;
import org.Almacen.TopAlmacen.Model.Panels;
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
        lst = new ArrayList<>();
        lst.add(new Panels(1,"messi","e"));
        lst.add(new Panels(2,"ronaldo","e"));
        lst.add(new Panels(3,"Cristiano","e"));
        System.out.println(lst);
    }


    public  String initi2(){
        DefaultSubMenu firstSubmenu = DefaultSubMenu.builder().label("wa").expanded(true).build();
            DefaultSubMenu itemMenu = DefaultSubMenu.builder().label("item").build();
            firstSubmenu.getElements().add(itemMenu);
            itemMenu = DefaultSubMenu.builder().label("we").build();
            firstSubmenu.getElements().add(itemMenu);

        return "Gestion/Dashboard";
    }


}
