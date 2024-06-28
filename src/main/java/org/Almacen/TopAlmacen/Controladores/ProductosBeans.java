package org.Almacen.TopAlmacen.Controladores;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import org.Almacen.TopAlmacen.DTO.Categoria.CategoriaDto;
import org.Almacen.TopAlmacen.DTO.Marca.CreateMarcaDto;
import org.Almacen.TopAlmacen.DTO.Marca.MarcaDto;
import org.Almacen.TopAlmacen.DTO.Producto.CreateProductoDto;
import org.Almacen.TopAlmacen.DTO.Producto.ProductoDto;
import org.Almacen.TopAlmacen.Services.CategoriaService;
import org.Almacen.TopAlmacen.Services.MarcaService;
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

    @Inject
    private CategoriaService categoriaService;

    @Inject
    private MarcaService marcaService;

    private ProductoDto productoDto;

    private int productoid;

    private int categoriaid;

    private int marcaid;

    private List<ProductoDto> productosProductoDtoList;

    private List<ProductoDto> productoDtoListSeleccionable;

    private List<CategoriaDto> categoriaDtoListActiva;

    private List<MarcaDto> marcaDtoListActiva;

    @PostConstruct
    private void init() {
        loadProductos();
    }

    public void nuevoProducto() {
        productoDto = new ProductoDto();
        categoriaDtoListActiva = categoriaService.getAllCategoriasActivas();
        marcaDtoListActiva = marcaService.getAllMarcaActiva();
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
        try {
            productosProductoDtoList = productoService.getAllProducto();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createProducto() {
        CreateProductoDto createProductoDto = new CreateProductoDto();
        createProductoDto.setNombre(productoDto.getNombre());
        createProductoDto.setMarca(productoDto.);



       /*
       private String nombre;
    private String color;
    private String peso;
    private CategoriaDto categoriaDto;
    private MarcaDto marcaDto;
        */



        /*createMarcaDto.setNombre(marcaDto.getNombre());
        createMarcaDto.setEstado("Activo");
        marcaService.createMarca(createMarcaDto);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("¡La marca "+createMarcaDto.getNombre()+" ha sido registrado exitosamente en el sistema!"));
    */

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
