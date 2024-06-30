package org.Almacen.TopAlmacen.Controladores.Categoria;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import org.Almacen.TopAlmacen.DTO.Categoria.CategoriaConProductosDto;
import org.Almacen.TopAlmacen.DTO.Categoria.CategoriaDto;
import org.Almacen.TopAlmacen.DTO.Categoria.CreateCategoriaDto;
import org.Almacen.TopAlmacen.DTO.Categoria.UpdateCategoriaDto;
import org.Almacen.TopAlmacen.DTO.Producto.ProductoDto;
import org.Almacen.TopAlmacen.Services.CategoriaService;
import org.primefaces.PrimeFaces;
import org.primefaces.util.LangUtils;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

@Data
@Named("CategoriaBeans")
@ViewScoped
public class CategoriaBeans implements Serializable {

    @Inject
    private CategoriaService categoriaService;
    private CategoriaDto categoriaDto;
    private List<CategoriaDto> categorias;
    private List<CategoriaDto> categoriasSelecionadas;
    private List<ProductoDto> productoDtoList;
    private List<ProductoDto> productoDtoListSeleccionado;
    private boolean renderedCategoria;
    private boolean renderedProducto;
    private boolean renderedBtnGuardar;
    private int categoriaId;

    @PostConstruct
    public void init() {
        loadCategorias();
    }

    public void nuevaCategoria() {
        categoriaDto = new CategoriaDto();
        validacionRenderVista(1);
    }

    public void determinarAccion() {
        if (categoriaDto.getId() == 0) {
            createCategoria();
        } else {
            updateCategoria();
        }
        loadCategorias();
        PrimeFaces.current().executeScript("PF('dialogsa').hide()");
        PrimeFaces.current().ajax().update(":form-datos:messages", ":form-datos:tabla");
    }


    private void loadCategorias() {
        try {
            categorias = categoriaService.getAllCategorias();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createCategoria() {
        CreateCategoriaDto categoriaCreate = new CreateCategoriaDto();
        categoriaCreate.setNombre(categoriaDto.getNombre());
        categoriaCreate.setDescripcion(categoriaDto.getDescripcion());
        categoriaCreate.setEstado("Activo");
        categoriaService.createCategoria(categoriaCreate);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("¡La categoria "+categoriaCreate.getNombre()+" ha sido registrado exitosamente en el sistema!"));
    }
    public void cargarCategoriaConProductos(){
        validacionRenderVista(2);
        categoriaDto = new CategoriaDto();
        CategoriaConProductosDto categoriaConProductosDto =  categoriaService.getCategoriaById(categoriaId);
        categoriaDto.setId(categoriaConProductosDto.getId());
        categoriaDto.setNombre(categoriaConProductosDto.getNombre());
        categoriaDto.setDescripcion(categoriaConProductosDto.getDescripcion());
        categoriaDto.setEstado(categoriaConProductosDto.getEstado());
        productoDtoList = categoriaConProductosDto.getProductos();
    }

    public void cargarCategoriaParaEdicion() {
        validacionRenderVista(1);
        categoriaDto = new CategoriaDto();
        var categoria = categoriaService.getCategoriaById(categoriaId);
        System.out.println(categoria.getNombre());
        if (categoria != null) {
            categoriaDto.setId(categoria.getId());
            categoriaDto.setNombre(categoria.getNombre());
            categoriaDto.setDescripcion(categoria.getDescripcion());
            categoriaDto.setEstado(categoria.getEstado());
        }
    }

    private void updateCategoria() {
        UpdateCategoriaDto CategoriaUpdate = new UpdateCategoriaDto();
        CategoriaUpdate.setNombre(categoriaDto.getNombre());
        CategoriaUpdate.setDescripcion(categoriaDto.getDescripcion());
        CategoriaUpdate.setEstado(categoriaDto.getEstado());
        categoriaService.updateCategoria(categoriaId, CategoriaUpdate);
        loadCategorias();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("¡La categoria "+CategoriaUpdate.getNombre()+" ha sido actualizado exitosamente en el sistema!"));
    }

    public void eliminarCategoria() {
        categoriaService.deleteCategoria(categoriaId);
        loadCategorias();
    }

    public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
        if (LangUtils.isValueBlank(filterText)) {
            return true;
        }
        int filterInt = getInteger(filterText);
        CategoriaDto c = (CategoriaDto) value;
        return (c.getId() >= filterInt && c.getId() <= filterInt)
                || c.getNombre().toLowerCase().contains(filterText)
                || c.getDescripcion().toLowerCase().contains(filterText);
    }

    private int getInteger(String string) {
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException ex) {
            return 0;
        }
    }

    private void validacionRenderVista(int opcion){
        switch (opcion){
            case 1:
                renderedCategoria = false;
                renderedProducto =false;
                renderedBtnGuardar=true;
                break;
            case 2:
                renderedCategoria = true;
                renderedProducto = true;
                renderedBtnGuardar=false;
                break;
        }
    }

    public void cambiarEstado(){
        categoriaDto = new CategoriaDto();
        var categoria = categoriaService.getCategoriaById(categoriaId);
        String estado = "";
        switch (categoria.getEstado()){
            case "Activo":
                estado = "Inactivo";
                break;
            case "Inactivo":
                estado = "Activo";
                break;
        }
        categoriaService.cambioEstado(categoriaId,estado);
        loadCategorias();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("¡El estado de la marca " + categoria.getNombre() + " ha cambiado a " + estado + "!"));
        PrimeFaces.current().executeScript("PF('dialogsa').hide()");
        PrimeFaces.current().ajax().update(":form-datos:messages", ":form-datos:tabla");
    }

}
