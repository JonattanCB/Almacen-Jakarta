package org.Almacen.TopAlmacen.Controladores.Producto;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import org.Almacen.TopAlmacen.DTO.Categoria.CategoriaDto;
import org.Almacen.TopAlmacen.DTO.Marca.MarcaDto;
import org.Almacen.TopAlmacen.DTO.Producto.CreateProductoDto;
import org.Almacen.TopAlmacen.DTO.Producto.ProductoDto;
import org.Almacen.TopAlmacen.DTO.Producto.UpdateProductoDto;
import org.Almacen.TopAlmacen.Mappers.CategoriaMapper;
import org.Almacen.TopAlmacen.Mappers.MarcaMapper;
import org.Almacen.TopAlmacen.Mappers.ProductoMapper;
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
@ViewScoped
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

    private boolean btnguardar;

    private boolean EscribirDatos;

    private boolean btnNuevoProducto;

    private List<ProductoDto> productosProductoDtoList;

    private List<ProductoDto> productoDtoListSeleccionable;

    private List<CategoriaDto> categoriaDtoListActiva;

    private List<MarcaDto> marcaDtoListActiva;

    @PostConstruct
    private void init() {
        loadProductos();
        verificarNuevoBoton();
    }

    public void nuevoProducto() {
        productoDto = new ProductoDto();
        categoriaDtoListActiva = categoriaService.getAllCategoriasActivas();
        marcaDtoListActiva = marcaService.getAllMarcaActiva();
        categoriaid = 0 ;
        marcaid = 0;
        validarOpcion(1);
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

    private void cargarProductos(){
        categoriaDtoListActiva = categoriaService.getAllCategoriasActivas();
        marcaDtoListActiva = marcaService.getAllMarcaActiva();
        productoDto = productoService.getProductoById(productoid);
        categoriaid = productoDto.getCategoria().getId();
        marcaid = productoDto.getMarca().getId();
    }

    public void cargarProductoEdicion(){
        cargarProductos();
        validarOpcion(1);
    }

    public void verProducto(){
        cargarProductos();
        validarOpcion(2);
    }

    public void eliminarProducto(){
        ProductoDto p = productoService.getProductoById(productoid);
        productoService.deleteProducto(productoid);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("¡El producto " + p.getNombre() + " ha sido eliminado exitosamente del sistema!"));
        loadProductos();
        PrimeFaces.current().executeScript("PF('dialogsa').hide()");
        PrimeFaces.current().ajax().update(":form-datos:messages", ":form-datos:tabla");
    }

    private void createProducto() {
        CreateProductoDto createProductoDto = new CreateProductoDto();
        createProductoDto.setNombre(productoDto.getNombre());
        createProductoDto.setColor(productoDto.getColor());
        createProductoDto.setPeso(productoDto.getPeso());
        createProductoDto.setCategoria(CategoriaMapper.toCategoria(categoriaService.getCategoria(categoriaid)));
        createProductoDto.setMarca(MarcaMapper.toMarca(marcaService.getMarcaById(marcaid)));
        productoService.createProducto(createProductoDto);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("¡El producto "+createProductoDto.getNombre()+" ha sido registrado exitosamente en el sistema!"));
    }


    private void UpdateProducto() {
        UpdateProductoDto updateProductoDto = new UpdateProductoDto();
        updateProductoDto.setNombre(productoDto.getNombre());
        updateProductoDto.setColor(productoDto.getColor());
        updateProductoDto.setPeso(productoDto.getPeso());
        updateProductoDto.setCategoria(CategoriaMapper.toCategoria(categoriaService.getCategoria(categoriaid)));
        updateProductoDto.setMarca(MarcaMapper.toMarca(marcaService.getMarcaById(marcaid)));
        productoService.updateProducto(productoid, updateProductoDto);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("¡El Producto "+updateProductoDto.getNombre()+" ha sido actualizado exitosamente en el sistema!"));
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

    private  void validarOpcion(int opcion){
        switch (opcion){
            case 1:
                btnguardar = true;
                EscribirDatos = false;
                break;
            case 2:
                btnguardar = false;
                EscribirDatos = true;
                break;
        }
    }

    private void verificarNuevoBoton(){
        List<CategoriaDto> categoriaDtoList = categoriaService.getAllCategoriasActivas();
        List<MarcaDto> marcaDtoList = marcaService.getAllMarcaActiva();

        if (categoriaDtoList.isEmpty() || marcaDtoList.isEmpty()){
            btnNuevoProducto = true;
        }else {
            btnNuevoProducto = false;
        }
    }

}
