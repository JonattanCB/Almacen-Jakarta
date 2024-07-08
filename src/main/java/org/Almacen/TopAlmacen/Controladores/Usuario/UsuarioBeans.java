package org.Almacen.TopAlmacen.Controladores.Usuario;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import org.Almacen.TopAlmacen.DTO.Categoria.CategoriaDto;
import org.Almacen.TopAlmacen.DTO.Dependencia.DependenciaConUnidadesDto;
import org.Almacen.TopAlmacen.DTO.Dependencia.DependenciaDto;
import org.Almacen.TopAlmacen.DTO.Rol.RolDto;
import org.Almacen.TopAlmacen.DTO.UnidadDependencia.UnidadDependenciaDto;
import org.Almacen.TopAlmacen.DTO.Usuario.CreateUsuarioDto;
import org.Almacen.TopAlmacen.DTO.Usuario.UpdateUsuarioDto;
import org.Almacen.TopAlmacen.DTO.Usuario.UsuarioDto;
import org.Almacen.TopAlmacen.Mappers.UnidadDependenciaMapper;
import org.Almacen.TopAlmacen.Model.UnidadDependencia;
import org.Almacen.TopAlmacen.Services.DependenciaService;
import org.Almacen.TopAlmacen.Services.RolService;
import org.Almacen.TopAlmacen.Services.UnidadService;
import org.Almacen.TopAlmacen.Services.UsuarioService;
import org.primefaces.PrimeFaces;
import org.primefaces.util.LangUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Data
@Named("UsuarioBeans")
@ViewScoped
public class UsuarioBeans implements Serializable {

    @Inject
    private UnidadService unidadService;

    @Inject
    private DependenciaService dependenciaService;

    @Inject
    private UsuarioService usuarioService;

    private UsuarioDto usuarioDto;

    private int idUsuario;

    private int idependencia;

    private int idUnidadDependencia;

    private boolean validacionDependencia;

    private boolean validacionUnidad;

    private boolean validacionUsuario;

    private List<UsuarioDto> usuarioDtoList;

    private List<UsuarioDto> usuarioDtoListSeleccion;

    private List<DependenciaDto> dependenciaDtoList;

    private List<UnidadDependenciaDto> unidadDependenciaDtos;

    @PostConstruct
    private void init() {
       loadUsuario();
    }

    public void nuevoUsuario(){
        usuarioDto = new UsuarioDto();
        dependenciaDtoList = dependenciaService.getAllActivos();
        idependencia = 0;
        ValidacionDependencia(1);
        ValidacionVerDatos(1);
    }

    public void DeterminarAccion(){
        if (usuarioDto.getId() ==0){
            CreateUsuario();
        }else{
            UpdateUsuario();
        }
        loadUsuario();
        PrimeFaces.current().executeScript("PF('dialogsa').hide()");
        PrimeFaces.current().ajax().update(":form-datos:messages", ":form-datos:tabla");
    }

    public void cargarUnidadDepenndencia(){
        unidadDependenciaDtos = unidadService.getAllByDependencia(idependencia);
        ValidacionDependencia(2);
    }


    public void cargarUsuarioVista(){
        cargarUsuario();
        ValidacionVerDatos(2);
        ValidacionDependencia(3);
    }




    private void cargarUsuario(){
        usuarioDto = usuarioService.getUsuarioById(idUsuario);
        dependenciaDtoList = dependenciaService.getAllActivos();
        idependencia = usuarioDto.getUnidad().getDependencia().getId();
        unidadDependenciaDtos = unidadService.getAllByDependencia(idependencia);
        idUnidadDependencia = usuarioDto.getUnidad().getId();
    }

    public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
        if (LangUtils.isValueBlank(filterText)) {
            return true;
        }
        int filterInt = getInteger(filterText);
        UsuarioDto c = (UsuarioDto) value;
        return (c.getId() >= filterInt && c.getId() <= filterInt)
                || (c.getNombres() + " "+c.getApellidos()).toLowerCase().contains(filterText)
                || c.getUnidad().getNombre().toLowerCase().contains(filterText);
    }

    private int getInteger(String string) {
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException ex) {
            return 0;
        }
    }


    private void loadUsuario(){
        try {
            usuarioDtoList = usuarioService.getAllUsuario();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void CreateUsuario(){
        CreateUsuarioDto dto = new CreateUsuarioDto();
        dto.setNombres(usuarioDto.getNombres());
        dto.setApellidos(usuarioDto.getApellidos());
        dto.setContra(usuarioDto.getContra());
        dto.setCorreo(usuarioDto.getCorreo());
        dto.setEstado("Activo");
        dto.setUnidad(UnidadDependenciaMapper.toEntity(unidadService.getById(idUnidadDependencia)));
        usuarioService.createUsuario(dto);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("¡El usuario " + (dto.getNombres() +" "+dto.getApellidos()) + " ha sido registrado exitosamente en el sistema!"));
    }

    private void UpdateUsuario(){
        UpdateUsuarioDto dto = new UpdateUsuarioDto();
        dto.setNombres(usuarioDto.getNombres());
        dto.setApellidos(usuarioDto.getApellidos());
        dto.setContra(usuarioDto.getContra());
        dto.setCorreo(usuarioDto.getCorreo());
        dto.setEstado("Activo");
        dto.setUnidad(UnidadDependenciaMapper.toEntity(unidadService.getById(idUnidadDependencia)));
        usuarioService.updateUsuario(dto,idUsuario);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("¡La usuario " + (dto.getNombres() +" "+dto.getApellidos())  + " ha sido actualizado exitosamente en el sistema!"));
    }

    private void ValidacionDependencia(int opcion){
        switch (opcion) {
            case 1:
                validacionDependencia = false;
                validacionUnidad   = true;
                break;
            case 2:
                validacionUnidad = false;
                validacionDependencia = true;
                break;
            case 3:
                validacionUnidad = true;
                validacionDependencia = true;
                break;
        }
    }

    private void ValidacionVerDatos(int opcion){
        switch (opcion){
            case 1:
                validacionUsuario = false;
                break;
            case 2:
                validacionUsuario = true;
                break;
        }
    }



}
