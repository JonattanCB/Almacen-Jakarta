package org.Almacen.TopAlmacen.Controladores;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import org.Almacen.TopAlmacen.DTO.Categoria.CategoriaDto;
import org.Almacen.TopAlmacen.DTO.Categoria.CreateCategoriaDto;
import org.Almacen.TopAlmacen.DTO.Marca.CreateMarcaDto;
import org.Almacen.TopAlmacen.DTO.Marca.MarcaDto;
import org.Almacen.TopAlmacen.Services.MarcaService;
import org.primefaces.PrimeFaces;
import org.primefaces.util.LangUtils;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

@Data
@Named("MarcaBeans")
@SessionScoped
public class MarcaBeans implements Serializable {

    @Inject
    private MarcaService marcaService;

    private List<MarcaDto> marcaDtoList;
    private List<MarcaDto> marcaDtoListSeleccionado;
    private MarcaDto marcaDto;
    private int idMarca;


    @PostConstruct
    private void init(){
        loadMarca();
    }

    public  void nuevaMarca(){
        marcaDto = new MarcaDto();
    }


    public void determinarAccion(){
        if (marcaDto.getId() == 0){
            createMarca();
        }else{

        }
        loadMarca();
        PrimeFaces.current().executeScript("PF('dialogsa').hide()");
        PrimeFaces.current().ajax().update(":form-datos:messages", ":form-datos:tabla");
    }

    public void loadMarca(){
        try {
            marcaDtoList = marcaService.getAllMarca();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void CargarMarcaraparaEdicion(){

    }

    public  void CambiarEstado(){

    }

    private void  createMarca(){
        CreateMarcaDto createMarcaDto = new CreateMarcaDto();
        createMarcaDto.setNombre(marcaDto.getNombre());
        createMarcaDto.setEstado("Activo");
        marcaService.createMarca(createMarcaDto);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("¡La marca "+createMarcaDto.getNombre()+" ha sido registrado exitosamente en el sistema!"));
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
                || m.getFechaRegistro().equals(filterText.toLowerCase());
    }

    private int getInteger(String string) {
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException ex) {
            return 0;
        }
    }


}
