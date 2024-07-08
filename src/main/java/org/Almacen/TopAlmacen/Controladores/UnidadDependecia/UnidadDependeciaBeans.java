package org.Almacen.TopAlmacen.Controladores.UnidadDependecia;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import org.Almacen.TopAlmacen.DTO.Categoria.CategoriaDto;
import org.Almacen.TopAlmacen.DTO.Dependencia.DependenciaDto;
import org.Almacen.TopAlmacen.DTO.Rol.RolDto;
import org.Almacen.TopAlmacen.DTO.UnidadDependencia.CreateUnidadDependenciaDto;
import org.Almacen.TopAlmacen.DTO.UnidadDependencia.UnidadDependenciaDto;
import org.Almacen.TopAlmacen.DTO.UnidadDependencia.UpdateUnidadDependenciaDto;
import org.Almacen.TopAlmacen.Mappers.DependenciaMapper;
import org.Almacen.TopAlmacen.Services.DependenciaService;
import org.Almacen.TopAlmacen.Services.RolService;
import org.Almacen.TopAlmacen.Services.UnidadService;
import org.primefaces.PrimeFaces;
import org.primefaces.util.LangUtils;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;


@Data
@Named("UnidadDependeciaBeans")
@ViewScoped
public class UnidadDependeciaBeans implements Serializable {

    @Inject
    private UnidadService unidadService;

    private List<UnidadDependenciaDto> UdependenciaDtoList;

    private List<UnidadDependenciaDto> UdependenciaDtoListSeleccion;


    @PostConstruct
    public void init() {
        loadUnidadDependencia();
    }


    public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
        if (LangUtils.isValueBlank(filterText)) {
            return true;
        }
        int filterInt = getInteger(filterText);
        UnidadDependenciaDto c = (UnidadDependenciaDto) value;
        return (c.getId() >= filterInt && c.getId() <= filterInt)
                || c.getNombre().toLowerCase().contains(filterText)
                || (c.getDependencia().getNombre()).toLowerCase().contains(filterText)
                || String.valueOf(c.getFechaRegistro()).toLowerCase().contains(filterText);
    }

    private void loadUnidadDependencia(){
        UdependenciaDtoList = unidadService.getAll();
    }

    private int getInteger(String string) {
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException ex) {
            return 0;
        }
    }

}
