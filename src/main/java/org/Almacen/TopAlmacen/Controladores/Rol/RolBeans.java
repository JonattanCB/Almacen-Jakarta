package org.Almacen.TopAlmacen.Controladores.Rol;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import org.Almacen.TopAlmacen.DTO.Rol.CreateRolDto;
import org.Almacen.TopAlmacen.DTO.Rol.RolDto;
import org.Almacen.TopAlmacen.DTO.Rol.UpdateRolDto;
import org.Almacen.TopAlmacen.DTO.UnidadDependencia.UnidadDependenciaDto;
import org.Almacen.TopAlmacen.Mappers.UnidadDependenciaMapper;
import org.Almacen.TopAlmacen.Services.RolService;
import org.Almacen.TopAlmacen.Services.UnidadService;
import org.primefaces.PrimeFaces;
import org.primefaces.util.LangUtils;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

@Data
@Named("RolBeans")
@ViewScoped
public class RolBeans implements Serializable {

    @Inject
    private RolService rolService;

    @Inject
    private UnidadService unidadService;

    private RolDto rolDto;

    private int idRol;

    private int idUnidad;

    private List<RolDto> rolDtoList;

    private List<RolDto> rolDtoListSelect;

    private List<UnidadDependenciaDto> unidadDependenciaDtos;

    @PostConstruct
    private void init(){
        loadRoles();
    }

    private  void loadRoles(){
        try {
            rolDtoList = rolService.getAllRol();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void NuevoRoles(){
        rolDto = new RolDto();
        unidadDependenciaDtos = unidadService.getAll();
        idUnidad = 0;
    }

    public void DeterminarAccion(){
        if (rolDto.getId() == 0){
            createrol();
        }else {
            updaterol();
        }
        loadRoles();
        PrimeFaces.current().executeScript("PF('dialogsa').hide()");
        PrimeFaces.current().ajax().update(":form-datos:messages", ":form-datos:tabla");
    }

    private void createrol(){
        CreateRolDto createRolDto = new CreateRolDto();
        createRolDto.setNombre(rolDto.getNombre());
        createRolDto.setEstado("Activo");
        createRolDto.setUnidadDependencia(UnidadDependenciaMapper.toEntity(unidadService.getById(idUnidad)));
        rolService.createRol(createRolDto);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("¡La categoria "+createRolDto.getNombre()+" ha sido registrado exitosamente en el sistema!"));
    }

    private  void updaterol(){
        UpdateRolDto updateRolDto = new UpdateRolDto();
        updateRolDto.setNombre(rolDto.getNombre());
        updateRolDto.setEstado(rolDto.getEstado());
        updateRolDto.setUnidadDependencia(UnidadDependenciaMapper.toEntity(unidadService.getById(idUnidad)));
        rolService.updateRol(updateRolDto, idRol);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("¡La categoria "+updateRolDto.getNombre()+" ha sido actualizado exitosamente en el sistema!"));
    }

    public void cargarRolEdiccion(){
        rolDto = rolService.getRolById(idRol);
        unidadDependenciaDtos = unidadService.getAll();
        idUnidad = rolDto.getUnidadDependencia().getId();
    }
    
    public  void cambiarEstado(){
        rolDto = rolService.getRolById(idRol);
        String estado = "";
        switch (rolDto.getEstado()){
            case "Activo":
                estado = "Inactivo";
                break;
            case "Inactivo":
                estado = "Activo";
                break;
        }
        rolService.cambiarEstado(idRol,estado);
        loadRoles();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("¡El estado del rol " + rolDto.getNombre() + " ha cambiado a " + estado + "!"));
        PrimeFaces.current().executeScript("PF('dialogsa').hide()");
        PrimeFaces.current().ajax().update(":form-datos:messages", ":form-datos:tabla");

    }

    public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
        if (LangUtils.isValueBlank(filterText)) {
            return true;
        }
        int filterInt = getInteger(filterText);
        RolDto c = (RolDto) value;
        return (c.getId() >= filterInt && c.getId() <= filterInt)
                || c.getNombre().toLowerCase().contains(filterText)
                || c.getEstado().toLowerCase().contains(filterText);
    }

    private int getInteger(String string) {
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException ex) {
            return 0;
        }
    }



}
