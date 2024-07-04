package org.Almacen.TopAlmacen.Controladores.UnidadDependecia;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import org.Almacen.TopAlmacen.DTO.Categoria.CategoriaDto;
import org.Almacen.TopAlmacen.DTO.Dependencia.DependenciaDto;
import org.Almacen.TopAlmacen.DTO.Rol.RolDto;
import org.Almacen.TopAlmacen.DTO.UnidadDependencia.UnidadDependenciaDto;
import org.Almacen.TopAlmacen.Services.DependenciaService;
import org.Almacen.TopAlmacen.Services.RolService;
import org.Almacen.TopAlmacen.Services.UnidadService;
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

    @Inject
    private RolService rolService;

    private DependenciaService dependenciaService;

    private UnidadDependenciaDto unidadDependenciaDto;

    private boolean btnNuevaUD;

    private List<UnidadDependenciaDto> unidadDependenciaDtos;

    private List<UnidadDependenciaDto> unidadDependenciaDtosSeleccion;

    private List<RolDto> rolDtoList;

    private List<DependenciaDto> dependenciaDtos;

    @PostConstruct
    private void init(){
        loadunidadDependecia();
        validacionNuevaUnidad();
    }

    public void nuevaUnidadDependecia(){
        unidadDependenciaDto = new UnidadDependenciaDto();
    }




    private  void loadunidadDependecia(){
        try{
            unidadDependenciaDtos = unidadService.getAll();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    private void validacionNuevaUnidad(){
         List<RolDto> rolDtos = rolService.getAllRol();
         List<DependenciaDto> dependenciaDtoList = dependenciaService.getAll();
         if (rolDtos.isEmpty() || dependenciaDtoList.isEmpty()){
             btnNuevaUD = true;
         }else{
             btnNuevaUD = false;
         }
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
                || (c.getDependencia().getNombre()).toLowerCase().contains(filterText);
    }

    private int getInteger(String string) {
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException ex) {
            return 0;
        }
    }


}
