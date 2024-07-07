package org.Almacen.TopAlmacen.Controladores.Dependencia;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import org.Almacen.TopAlmacen.DTO.Dependencia.CreateDependenciaDto;
import org.Almacen.TopAlmacen.DTO.Dependencia.DependenciaDto;
import org.Almacen.TopAlmacen.DTO.Dependencia.UpdateDependenciaDto;
import org.Almacen.TopAlmacen.Services.DependenciaService;
import org.primefaces.PrimeFaces;
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

    private DependenciaDto dependenciaDto;

    private int idDependencia;

    private List<DependenciaDto> dependenciaDtoList;

    private List<DependenciaDto> dependenciaDtoListSeleccionado;

    @PostConstruct
    private void init(){
        loadDependenciaDto();
    }

    public void nuevadependecia(){
        dependenciaDto = new DependenciaDto();
    }

    public  void DeterminarAccion(){
        if (dependenciaDto.getId() == 0){
            createDependenciaDto();
        }else{
            updateDependenciaDto();
        }
        loadDependenciaDto();
        PrimeFaces.current().executeScript("PF('dialogsa').hide()");
        PrimeFaces.current().ajax().update(":form-datos:messages", ":form-datos:tabla");
    }

    public  void CargaDatos(){
        dependenciaDto = dependenciaService.getByIdDependencia(idDependencia);
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
                || c.getEstado().toLowerCase().contains(filterText);
    }

    public void cambioEstado(){
        dependenciaDto = dependenciaService.getByIdDependencia(idDependencia);
        String estado;
        switch (dependenciaDto.getEstado()){
            case "Activo":
                estado = "Inactivo";
                break;
            case "Inactivo":
                estado = "Activo";
                break;
            default:
                estado = dependenciaDto.getEstado();
        }
        dependenciaService.cambioEstado(estado, idDependencia);
        loadDependenciaDto();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("¡El estado de la dependencia " + dependenciaDto.getNombre() + " ha cambiado a " + estado + "!"));
        PrimeFaces.current().executeScript("PF('dialogsa').hide()");
        PrimeFaces.current().ajax().update(":form-datos:messages", ":form-datos:tabla");
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

    private void createDependenciaDto(){
        CreateDependenciaDto  createDependenciaDto = new CreateDependenciaDto();
        createDependenciaDto.setNombre(dependenciaDto.getNombre());
        createDependenciaDto.setEstado("Activo");
        dependenciaService.create(createDependenciaDto);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("¡La dependencia " + createDependenciaDto.getNombre() + " ha sido registrado exitosamente en el sistema!"));
    }

    private void updateDependenciaDto(){
        UpdateDependenciaDto updateDependenciaDto = new UpdateDependenciaDto();
        updateDependenciaDto.setNombre(dependenciaDto.getNombre());
        updateDependenciaDto.setEstado(dependenciaDto.getEstado());
        dependenciaService.update(updateDependenciaDto, idDependencia);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("¡La dependencia " + updateDependenciaDto.getNombre() + " ha sido actualizado exitosamente en el sistema!"));
    }





}
