package org.Almacen.TopAlmacen.Controladores.Dependencia;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import org.Almacen.TopAlmacen.DTO.Dependencia.DependenciaDto;
import org.Almacen.TopAlmacen.Services.DependenciaService;
import org.primefaces.util.LangUtils;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

@Data
@Named("DependenciaBeans")
@ViewScoped
public class DependenciaBeans implements Serializable {

    @Inject
    private DependenciaService dependenciaService;


    private List<DependenciaDto> dependenciaDtoList;

    private List<DependenciaDto> dependenciaDtoListSeleccionado;

    @PostConstruct
    private void init(){
        loadDependenciaDto();
    }


    public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
        if (LangUtils.isValueBlank(filterText)) {
            return true;
        }
        int filterInt = getInteger(filterText);
        DependenciaDto c = (DependenciaDto) value;
        return (c.getId() >= filterInt && c.getId() <= filterInt)
                || c.getNombre().toLowerCase().contains(filterText)
                || c.getEstado().toLowerCase().contains(filterText)
                || String.valueOf(c.getFechaRegistro()).contains(filterText);
    }

    private int getInteger(String string) {
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException ex) {
            return 0;
        }
    }

    private void loadDependenciaDto(){
        dependenciaDtoList = dependenciaService.getAll();
    }

}
