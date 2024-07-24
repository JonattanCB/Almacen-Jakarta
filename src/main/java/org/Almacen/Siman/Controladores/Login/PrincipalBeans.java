package org.Almacen.Siman.Controladores.Login;

import jakarta.annotation.PostConstruct;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import lombok.Data;
import org.Almacen.Siman.DTO.Usuario.UsuarioDto;

import java.io.Serializable;

@Data
@Named("PrincipalBeans")
@ViewScoped
public class PrincipalBeans implements Serializable {

    private UsuarioDto us;

    private boolean pantallaUnidad;

    private boolean pantallaJefatura;

    private boolean pantallaAlmacen;

    @PostConstruct
    private void init(){
        us  = (UsuarioDto) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        visualizacionDashboard();
    }

    private void visualizacionDashboard(){
         pantallaJefatura = false;
         pantallaUnidad = false;
         pantallaAlmacen = false;
        if (us.getUnidad().getNombre().equalsIgnoreCase("Jefatura") &&
                !us.getUnidad().getDependencia().getNombre().equalsIgnoreCase("Almacen")) {
            pantallaJefatura = true;
        } else if (us.getUnidad().getDependencia().getNombre().equalsIgnoreCase("Almacen")) {
            pantallaAlmacen = true;
        } else if (!us.getUnidad().getRol().getNombre().toLowerCase().contains("jef") &&
                !us.getUnidad().getDependencia().getNombre().equalsIgnoreCase("Almacen")) {
            pantallaUnidad = true;
        }
    }

}
