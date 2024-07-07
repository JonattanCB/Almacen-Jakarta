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

    @Inject
    private DependenciaService dependenciaService;

    private UnidadDependenciaDto unidadDependenciaDto;

    private int idUnidadDependenciaDTO;

    private int idDependencia;

    private boolean btnNuevoUnidadDependencia;

    private List<UnidadDependenciaDto> UdependenciaDtoList;

    private List<UnidadDependenciaDto> UdependenciaDtoListSeleccion;

    private List<DependenciaDto> dependenciaDtos;

    @PostConstruct
    public void init() {
        loadUnidadDependencia();
        verificarDependencia();
    }

    public void nuevaUnidadDependencia(){
        unidadDependenciaDto = new UnidadDependenciaDto();
        dependenciaDtos = dependenciaService.getAllActivos();
        idDependencia = 0;
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
                || c.getDependencia().getNombre().toLowerCase().contains(filterText)
                || String.valueOf(c.getFechaRegistro()).toLowerCase().contains(filterText);
    }


    public void DeterminarAcccion(){
        if(unidadDependenciaDto.getId() == 0){
            createUnidadDependencia();
        }else{
            UpdateUnidadDependencia();
        }
        loadUnidadDependencia();
        PrimeFaces.current().executeScript("PF('dialogsa').hide()");
        PrimeFaces.current().ajax().update(":form-datos:messages", ":form-datos:tabla");
    }

    public void eliminarUnidadDependencia(){

    }

    public void CargarDatos(){
        unidadDependenciaDto = unidadService.getById(idUnidadDependenciaDTO);
        dependenciaDtos = dependenciaService.getAllActivos();
        idDependencia = unidadDependenciaDto.getDependencia().getId();
        System.out.println(unidadDependenciaDto.getId());
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

    private void createUnidadDependencia(){
        CreateUnidadDependenciaDto createUnidadDependenciaDto = new CreateUnidadDependenciaDto();
        createUnidadDependenciaDto.setNombre(unidadDependenciaDto.getNombre());
        createUnidadDependenciaDto.setDependencia(DependenciaMapper.toEntity(dependenciaService.getByIdDependencia(idDependencia)));
        unidadService.createUnidad(createUnidadDependenciaDto);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("¡La Unidad " + createUnidadDependenciaDto.getNombre() + " ha sido registrado exitosamente en el sistema!"));
    }

    private void UpdateUnidadDependencia(){
        UpdateUnidadDependenciaDto updateUnidadDependenciaDto = new UpdateUnidadDependenciaDto();
        updateUnidadDependenciaDto.setNombre(unidadDependenciaDto.getNombre());
        updateUnidadDependenciaDto.setDependecia(DependenciaMapper.toEntity(dependenciaService.getByIdDependencia(idDependencia)));
        unidadService.updateCategoria(idUnidadDependenciaDTO, updateUnidadDependenciaDto);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("¡La Unidad " + updateUnidadDependenciaDto.getNombre() + " ha sido actualizado exitosamente en el sistema!"));
    }

    private  void verificarDependencia(){
        dependenciaDtos = dependenciaService.getAllActivos();
        btnNuevoUnidadDependencia = dependenciaDtos.isEmpty();
    }






}
