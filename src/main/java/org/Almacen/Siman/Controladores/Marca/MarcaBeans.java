package org.Almacen.Siman.Controladores.Marca;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import org.Almacen.Siman.DTO.Marca.CreateMarcaDto;
import org.Almacen.Siman.DTO.Marca.MarcaDto;
import org.Almacen.Siman.DTO.Marca.UpdateMarcaDto;
import org.Almacen.Siman.DTO.Usuario.UsuarioDto;
import org.Almacen.Siman.Services.MarcaService;
import org.primefaces.PrimeFaces;
import org.primefaces.util.LangUtils;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

@Data
@Named("MarcaBeans")
@ViewScoped
public class MarcaBeans implements Serializable {

    @Inject
    private MarcaService marcaService;

    private List<MarcaDto> marcaDtoList;

    private List<MarcaDto> marcaDtoListSeleccionado;

    private MarcaDto marcaDto;

    private int idMarca;

    private boolean renderBtnRol;


    @PostConstruct
    private void init(){
        loadMarca();
        validarRol();
    }

    public  void nuevaMarca(){
        marcaDto = new MarcaDto();
    }


    public void determinarAccion(){
        if (marcaDto.getNombre().equals("")){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Por favor, complete todos los campos requeridos.",null));
            PrimeFaces.current().ajax().update(":form-datos:messages");
        }else{
            if (marcaDto.getId() == 0){
                createMarca();
            }else{
                updateMarca();
            }
            loadMarca();
            PrimeFaces.current().executeScript("PF('dialogsa').hide()");
            PrimeFaces.current().ajax().update(":form-datos:messages", ":form-datos:tabla");
        }
    }

    public void loadMarca(){
        try {
            marcaDtoList = marcaService.getAllMarca();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void CargarMarcaraparaEdicion(){
        marcaDto = new MarcaDto();
        var marca  = marcaService.getMarcaById(idMarca);
        marcaDto.setId(marca.getId());
        marcaDto.setNombre(marca.getNombre());
        marca.setFechaRegistro(marca.getFechaRegistro());
        marca.setEstado(marca.getEstado());
    }

    public  void CambiarEstado(){
        marcaDto = new MarcaDto();
        var marca = marcaService.getMarcaById(idMarca);
        String estado = "";
        switch (marca.getEstado()){
            case "Activo":
                estado = "Inactivo";
                break;
            case "Inactivo":
                estado = "Activo";
                break;
        }
        marcaService.cambiarEstado(idMarca,estado);
        loadMarca();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("¡El estado de la marca " + marca.getNombre() + " ha cambiado a " + estado + "!"));
        PrimeFaces.current().executeScript("PF('dialogsa').hide()");
        PrimeFaces.current().ajax().update(":form-datos:messages", ":form-datos:tabla");
    }

    private void  createMarca(){
        CreateMarcaDto createMarcaDto = new CreateMarcaDto();
        createMarcaDto.setNombre(marcaDto.getNombre());
        createMarcaDto.setEstado("Activo");
        marcaService.createMarca(createMarcaDto);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("¡La marca "+createMarcaDto.getNombre()+" ha sido registrado exitosamente en el sistema!"));
    }

    private void updateMarca(){
        UpdateMarcaDto updateMarcaDto   = new UpdateMarcaDto();
        updateMarcaDto.setNombre(marcaDto.getNombre());
        updateMarcaDto.setEstado(marcaDto.getEstado());
        marcaService.updateMarca(updateMarcaDto, idMarca);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("¡La marca "+updateMarcaDto.getNombre()+" ha sido actualizado exitosamente en el sistema!"));
    }

    public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
        if (LangUtils.isValueBlank(filterText)) {
            return true;
        }
        int filterInt = getInteger(filterText);
        MarcaDto m = (MarcaDto) value;
        return (m.getId() >= filterInt && m.getId() <= filterInt)
                || m.getNombre().toLowerCase().contains(filterText)
                || m.getEstado().toLowerCase().contains(filterText)
                || String.valueOf(m.getFechaRegistro()).contains(filterText.toLowerCase());
    }

    private int getInteger(String string) {
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException ex) {
            return 0;
        }
    }

    private void validarRol(){
        UsuarioDto user = (UsuarioDto) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        if (user.getUnidad().getRol().getNombre().equalsIgnoreCase("Jefe")){
            renderBtnRol = true;
        }else{
            renderBtnRol = false;
        }
    }


}
