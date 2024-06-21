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


    private MenuModel model;

    private String Nombre;

    private boolean abrirPerfil;

    private List<Panels> list;


    @PostConstruct
    public void init() {
        Nombre = "Jonattan Sebastian Contreras Baltazar";

        model = new DefaultMenuModel();
        DefaultSubMenu firstSubmenu = DefaultSubMenu.builder().label("wa").expanded(true).build();
        DefaultMenuItem itemMenu = DefaultMenuItem.builder().value("pepe").icon("pi pi-home").url("http://localhost:8080/Almacen-Jakarta/protegido/Personal/j.xhtml").update("myform").build();
        firstSubmenu.getElements().add(itemMenu);



        itemMenu = DefaultMenuItem.builder().value("maria").value("pepe").icon("pi pi-home").url("http://localhost:8080/Almacen-Jakarta/protegido/Personal/k.xhtml").build();
        firstSubmenu.getElements().add(itemMenu);



        itemMenu = DefaultMenuItem.builder().value("maria").build();
        firstSubmenu.getElements().add(itemMenu);itemMenu = DefaultMenuItem.builder().value("maria").build();
        firstSubmenu.getElements().add(itemMenu);
        firstSubmenu.getElements().add(itemMenu);itemMenu = DefaultMenuItem.builder().value("maria").build();
        firstSubmenu.getElements().add(itemMenu);firstSubmenu.getElements().add(itemMenu);itemMenu = DefaultMenuItem.builder().value("maria").build();
        firstSubmenu.getElements().add(itemMenu);firstSubmenu.getElements().add(itemMenu);itemMenu = DefaultMenuItem.builder().value("maria").build();
        firstSubmenu.getElements().add(itemMenu);firstSubmenu.getElements().add(itemMenu);itemMenu = DefaultMenuItem.builder().value("maria").build();
        firstSubmenu.getElements().add(itemMenu);firstSubmenu.getElements().add(itemMenu);itemMenu = DefaultMenuItem.builder().value("maria").build();
        firstSubmenu.getElements().add(itemMenu);firstSubmenu.getElements().add(itemMenu);itemMenu = DefaultMenuItem.builder().value("maria").build();
        firstSubmenu.getElements().add(itemMenu);firstSubmenu.getElements().add(itemMenu);itemMenu = DefaultMenuItem.builder().value("maria").build();
        firstSubmenu.getElements().add(itemMenu);firstSubmenu.getElements().add(itemMenu);itemMenu = DefaultMenuItem.builder().value("maria").build();
        firstSubmenu.getElements().add(itemMenu);firstSubmenu.getElements().add(itemMenu);itemMenu = DefaultMenuItem.builder().value("maria").build();
        firstSubmenu.getElements().add(itemMenu);firstSubmenu.getElements().add(itemMenu);itemMenu = DefaultMenuItem.builder().value("maria").build();
        firstSubmenu.getElements().add(itemMenu);firstSubmenu.getElements().add(itemMenu);itemMenu = DefaultMenuItem.builder().value("maria").build();
        firstSubmenu.getElements().add(itemMenu);firstSubmenu.getElements().add(itemMenu);itemMenu = DefaultMenuItem.builder().value("maria").build();
        firstSubmenu.getElements().add(itemMenu);firstSubmenu.getElements().add(itemMenu);itemMenu = DefaultMenuItem.builder().value("maria").build();
        firstSubmenu.getElements().add(itemMenu);firstSubmenu.getElements().add(itemMenu);itemMenu = DefaultMenuItem.builder().value("maria").build();
        firstSubmenu.getElements().add(itemMenu);firstSubmenu.getElements().add(itemMenu);itemMenu = DefaultMenuItem.builder().value("maria").build();
        firstSubmenu.getElements().add(itemMenu);firstSubmenu.getElements().add(itemMenu);itemMenu = DefaultMenuItem.builder().value("maria").build();
        firstSubmenu.getElements().add(itemMenu);firstSubmenu.getElements().add(itemMenu);itemMenu = DefaultMenuItem.builder().value("maria").build();
        firstSubmenu.getElements().add(itemMenu);firstSubmenu.getElements().add(itemMenu);itemMenu = DefaultMenuItem.builder().value("maria").build();
        firstSubmenu.getElements().add(itemMenu);firstSubmenu.getElements().add(itemMenu);itemMenu = DefaultMenuItem.builder().value("maria").build();
        firstSubmenu.getElements().add(itemMenu);firstSubmenu.getElements().add(itemMenu);itemMenu = DefaultMenuItem.builder().value("maria").build();
        firstSubmenu.getElements().add(itemMenu);firstSubmenu.getElements().add(itemMenu);itemMenu = DefaultMenuItem.builder().value("maria").build();
        firstSubmenu.getElements().add(itemMenu);firstSubmenu.getElements().add(itemMenu);itemMenu = DefaultMenuItem.builder().value("maria").build();
        firstSubmenu.getElements().add(itemMenu);

        DefaultSubMenu p = DefaultSubMenu.builder().label("we").expanded(true).build();

        model.getElements().add(firstSubmenu);
        model.getElements().add(p);

        list = new ArrayList<>();

        for (int id=1;id<=10;id++){
            list.add(new Panels(id,"nombre","descrcipcion","Activo",80));
            System.out.println(id);
        }




    }


    public  String initi2(){

        model = new DefaultMenuModel();
        DefaultSubMenu firstSubmenu = DefaultSubMenu.builder().label("wa").expanded(true).build();
            DefaultMenuItem itemMenu = DefaultMenuItem.builder().value("pepe").target("W").build();
            firstSubmenu.getElements().add(itemMenu);



            itemMenu = DefaultMenuItem.builder().value("maria").build();
            firstSubmenu.getElements().add(itemMenu);
        itemMenu = DefaultMenuItem.builder().value("maria").build();
        firstSubmenu.getElements().add(itemMenu);itemMenu = DefaultMenuItem.builder().value("maria").build();
        firstSubmenu.getElements().add(itemMenu);
        firstSubmenu.getElements().add(itemMenu);itemMenu = DefaultMenuItem.builder().value("maria").build();
        firstSubmenu.getElements().add(itemMenu);firstSubmenu.getElements().add(itemMenu);itemMenu = DefaultMenuItem.builder().value("maria").build();
        firstSubmenu.getElements().add(itemMenu);firstSubmenu.getElements().add(itemMenu);itemMenu = DefaultMenuItem.builder().value("maria").build();
        firstSubmenu.getElements().add(itemMenu);firstSubmenu.getElements().add(itemMenu);itemMenu = DefaultMenuItem.builder().value("maria").build();
        firstSubmenu.getElements().add(itemMenu);firstSubmenu.getElements().add(itemMenu);itemMenu = DefaultMenuItem.builder().value("maria").build();
        firstSubmenu.getElements().add(itemMenu);firstSubmenu.getElements().add(itemMenu);itemMenu = DefaultMenuItem.builder().value("maria").build();
        firstSubmenu.getElements().add(itemMenu);firstSubmenu.getElements().add(itemMenu);itemMenu = DefaultMenuItem.builder().value("maria").build();
        firstSubmenu.getElements().add(itemMenu);firstSubmenu.getElements().add(itemMenu);itemMenu = DefaultMenuItem.builder().value("maria").build();
        firstSubmenu.getElements().add(itemMenu);firstSubmenu.getElements().add(itemMenu);itemMenu = DefaultMenuItem.builder().value("maria").build();
        firstSubmenu.getElements().add(itemMenu);firstSubmenu.getElements().add(itemMenu);itemMenu = DefaultMenuItem.builder().value("maria").build();
        firstSubmenu.getElements().add(itemMenu);firstSubmenu.getElements().add(itemMenu);itemMenu = DefaultMenuItem.builder().value("maria").build();
        firstSubmenu.getElements().add(itemMenu);firstSubmenu.getElements().add(itemMenu);itemMenu = DefaultMenuItem.builder().value("maria").build();
        firstSubmenu.getElements().add(itemMenu);firstSubmenu.getElements().add(itemMenu);itemMenu = DefaultMenuItem.builder().value("maria").build();
        firstSubmenu.getElements().add(itemMenu);firstSubmenu.getElements().add(itemMenu);itemMenu = DefaultMenuItem.builder().value("maria").build();
        firstSubmenu.getElements().add(itemMenu);firstSubmenu.getElements().add(itemMenu);itemMenu = DefaultMenuItem.builder().value("maria").build();
        firstSubmenu.getElements().add(itemMenu);firstSubmenu.getElements().add(itemMenu);itemMenu = DefaultMenuItem.builder().value("maria").build();
        firstSubmenu.getElements().add(itemMenu);firstSubmenu.getElements().add(itemMenu);itemMenu = DefaultMenuItem.builder().value("maria").build();
        firstSubmenu.getElements().add(itemMenu);firstSubmenu.getElements().add(itemMenu);itemMenu = DefaultMenuItem.builder().value("maria").build();
        firstSubmenu.getElements().add(itemMenu);firstSubmenu.getElements().add(itemMenu);itemMenu = DefaultMenuItem.builder().value("maria").build();
        firstSubmenu.getElements().add(itemMenu);firstSubmenu.getElements().add(itemMenu);itemMenu = DefaultMenuItem.builder().value("maria").build();
        firstSubmenu.getElements().add(itemMenu);firstSubmenu.getElements().add(itemMenu);itemMenu = DefaultMenuItem.builder().value("maria").build();
        firstSubmenu.getElements().add(itemMenu);firstSubmenu.getElements().add(itemMenu);itemMenu = DefaultMenuItem.builder().value("maria").build();
        firstSubmenu.getElements().add(itemMenu);

        DefaultSubMenu p = DefaultSubMenu.builder().label("we").expanded(true).build();

            model.getElements().add(firstSubmenu);
            model.getElements().add(p);
        return "Gestion/Dashboard";
    }

}
