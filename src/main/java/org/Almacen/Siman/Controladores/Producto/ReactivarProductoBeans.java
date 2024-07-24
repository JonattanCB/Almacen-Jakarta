package org.Almacen.Siman.Controladores.Producto;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import org.Almacen.Siman.DTO.Producto.ProductoDto;
import org.Almacen.Siman.Services.ProductoService;
import org.primefaces.PrimeFaces;
import org.primefaces.util.LangUtils;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

@Data
@Named("ReactivarProductoBean")
@ViewScoped
public class ReactivarProductoBeans implements Serializable {

    @Inject
    private ProductoService productoService;

    private int idProducto;

    private List<ProductoDto> productoDtoList;

    private List<ProductoDto> productoDtoListSeleccionado;

    @PostConstruct
    private void init(){
        loadProductoInactivos();
    }

    private void loadProductoInactivos(){
        try{
            productoDtoList = productoService.getAllFalseEstao();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
        if (LangUtils.isValueBlank(filterText)) {
            return true;
        }
        int filterInt = getInteger(filterText);
        ProductoDto p = (ProductoDto) value;
        return (p.getId() >= filterInt && p.getId() <= filterInt)
                || p.getNombre().toLowerCase().contains(filterText)
                || (p.getCategoria().getNombre()).toLowerCase().contains(filterText)
                || (p.getMarca().getNombre()).toLowerCase().contains(filterText)
                || String.valueOf(p.getFechaRegistro()).toLowerCase().contains(filterText);
    }

    private int getInteger(String string) {
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException ex) {
            return 0;
        }
    }

    public void reactivarProducto(){
        ProductoDto productoDto = productoService.getProductoById(idProducto);
        productoService.ChangeStateACTIVO(productoDto.getId());
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("¡El producto " + productoDto.getNombre() + " ha sido dado de alta exitosamente del sistema!"));
        loadProductoInactivos();
        PrimeFaces.current().executeScript("PF('dialogsa').hide()");
        PrimeFaces.current().ajax().update(":form-datos:messages", ":form-datos:tabla");
    }

}
