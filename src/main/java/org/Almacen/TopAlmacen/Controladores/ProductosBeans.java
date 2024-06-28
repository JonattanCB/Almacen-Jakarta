package org.Almacen.TopAlmacen.Controladores;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import org.Almacen.TopAlmacen.DTO.Marca.MarcaDto;
import org.Almacen.TopAlmacen.DTO.Producto.ProductoDto;
import org.Almacen.TopAlmacen.Services.ProductoService;
import org.primefaces.PrimeFaces;
import org.primefaces.util.LangUtils;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

@Data
@Named("ProductosBeans")
@SessionScoped
public class ProductosBeans implements Serializable {


    @Inject
    private ProductoService productoService;


    private ProductoDto productoDto;

    private List<ProductoDto> productosProductoDtoList;

    private List<ProductoDto> productoDtoListSeleccionable;

    @PostConstruct
    private void init() {
        loadProductos();
    }

    public void nuevoProducto() {
        productoDto = new ProductoDto();
    }

    public void DeterminarAccion() {
        if (productoDto.getId() == 0) {
            createProducto();
        } else {
            UpdateProducto();
        }
        loadProductos();
        PrimeFaces.current().executeScript("PF('dialogsa').hide()");
        PrimeFaces.current().ajax().update(":form-datos:messages", ":form-datos:tabla");
    }

    private void loadProductos() {

    }

    private void createProducto() {

    }

    private void UpdateProducto() {

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
                || p.getFechaRegistro().equals(filterText.toLowerCase());
    }

    private int getInteger(String string) {
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException ex) {
            return 0;
        }
    }


}
