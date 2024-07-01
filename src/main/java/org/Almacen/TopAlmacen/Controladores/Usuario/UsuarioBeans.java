package org.Almacen.TopAlmacen.Controladores.Usuario;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import org.Almacen.TopAlmacen.DTO.Dependencia.DependenciaConUnidadesDto;
import org.Almacen.TopAlmacen.DTO.Dependencia.DependenciaDto;
import org.Almacen.TopAlmacen.DTO.Rol.RolDto;
import org.Almacen.TopAlmacen.DTO.Usuario.UsuarioDto;
import org.Almacen.TopAlmacen.Services.DependenciaService;
import org.Almacen.TopAlmacen.Services.RolService;
import org.Almacen.TopAlmacen.Services.UsuarioService;
import org.primefaces.PrimeFaces;
import org.primefaces.util.LangUtils;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

@Data
@Named("UsuarioBeans")
@ViewScoped
public class UsuarioBeans implements Serializable {

    @Inject
    private UsuarioService usuarioService;

    @Inject
    private RolService rolService;

    @Inject
    private DependenciaService dependenciaService;

    private UsuarioDto usuarioDto;

    private DependenciaConUnidadesDto dependenciaConUnidadesDto;

    private int iddepende;

    private int idUsuario;

    private int idUnidad;

    private int idRol;

    private List<UsuarioDto> usuarios;

    private List<UsuarioDto> usuariosSeleccioando;

    private List<RolDto> rolesActivos;

    private List<DependenciaDto> dependenciaDtos;

    @PostConstruct
    private void  init(){
        loadUsuario();
    }

    private void  loadUsuario(){
        try{
            usuarios = usuarioService.getAllUsuario();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void NuevoUsuario(){
        usuarioDto = new UsuarioDto();
        rolesActivos = rolService.getAllRolActiva();
        dependenciaDtos = dependenciaService.getAll();
        iddepende = 0;
        idRol = 0;
    }

    public void DeterminarAccion(){
        if (usuarioDto.getId() == 0){
            CreateUsuario();
        }else{
            UpdateUsuario();
        }
        loadUsuario();
        PrimeFaces.current().executeScript("PF('dialogsa').hide()");
        PrimeFaces.current().ajax().update(":form-datos:messages", ":form-datos:tabla");
    }


    private  void CreateUsuario(){

    }

    private void UpdateUsuario(){

    }

    public void cargarUnidades(){
        dependenciaConUnidadesDto = dependenciaService.getById(iddepende);

    }

    public void CambiarEstado(){

    }

    public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
        if (LangUtils.isValueBlank(filterText)) {
            return true;
        }
        int filterInt = getInteger(filterText);
        UsuarioDto u = (UsuarioDto) value;
        return (u.getId() >= filterInt && u.getId() <= filterInt)
                ||(u.getNombres() +" "+u.getApellidos()).toLowerCase().contains(filterText)
                ||u.getRol().getNombre().toLowerCase().contains(filterText)
                || u.getEstado().toLowerCase().contains(filterText);
    }

    private int getInteger(String string) {
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException ex) {
            return 0;
        }
    }

}
