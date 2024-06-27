package org.Almacen.TopAlmacen.Controladores;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import org.Almacen.TopAlmacen.DTO.Categoria.CategoriaDatosDto;
import org.Almacen.TopAlmacen.DTO.Categoria.CategoriaDto;
import org.Almacen.TopAlmacen.DTO.Categoria.CreateCategoriaDto;
import org.Almacen.TopAlmacen.DTO.Categoria.UpdateCategoriaDto;
import org.Almacen.TopAlmacen.Services.CategoriaService;
import org.primefaces.PrimeFaces;
import org.primefaces.util.LangUtils;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

@Data
@Named("CategoriaBeans")
@SessionScoped
public class CategoriaBeans implements Serializable {

    @Inject
    private CategoriaService categoriaService;

    private CategoriaDatosDto categoriaDatosDto;
    private List<CategoriaDto> categorias;
    private List<CategoriaDto> categoriasSeleccionadas;
    private int categoriaId;

    @PostConstruct
    public void init() {
        loadCategorias();
    }

    public void nuevaCategoria() {
        categoriaDatosDto = new CategoriaDatosDto();
    }

    public void determinarAccion(){
        if (categoriaDatosDto.getId() == 0){
            createCategoria();
        }else{

        }
        loadCategorias();
        PrimeFaces.current().executeScript("PF('dialogsa').hide()");
        PrimeFaces.current().ajax().update(":form-datos:messages", ":form-datos:tabla");
    }


    private void loadCategorias() {
        try {
            var futureCategorias = categoriaService.getAllCategoriasAsync();
            categorias = futureCategorias.get();
            for (CategoriaDto d : categorias){
                System.out.println(d.getNombre());
            }
            System.out.println("-".repeat(199));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createCategoria() {
        CreateCategoriaDto categoriaCreate = new CreateCategoriaDto();
        categoriaCreate.setNombre(categoriaDatosDto.getNombre());
        categoriaCreate.setDescripcion(categoriaDatosDto.getDescripcion());
        categoriaCreate.setEstado("Activo");
        categoriaService.createCategoriaAsync(categoriaCreate);
        loadCategorias();
    }

    public void cargarCategoriaParaEdicion() {
        var categoria = categoriaService.getCategoriaById(categoriaId);
        if (categoria != null) {
           // categoriaDto.setNombre(categoria.getNombre());
            //categoriaDto.setDescripcion(categoria.getDescripcion());
            //categoriaDto.setEstado(categoria.getEstado());
        }
    }

    private void updateCategoria(int id) {
        UpdateCategoriaDto CategoriaUpdate = new UpdateCategoriaDto();
       /* CategoriaUpdate.setNombre(categoriaDto.getNombre());
        CategoriaUpdate.setDescripcion(categoriaDto.getDescripcion());
        CategoriaUpdate.setEstado(categoriaDto.getEstado());*/
        if (categoriaId != 0) {
            categoriaService.updateCategoriaAsync(id, CategoriaUpdate);
            loadCategorias();
        }
    }

    public void eliminarCategoria() {
        categoriaService.deleteCategoriaAsync(categoriaId);
        loadCategorias();
    }

    public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
        if (LangUtils.isValueBlank(filterText)) {
            return true;
        }
        int filterInt = getInteger(filterText);
        CategoriaDto c = (CategoriaDto) value;
        return  (c.getId()>=filterInt && c.getId()<=filterInt)
                ||c.getNombre().toLowerCase().contains(filterText)
                || c.getDescripcion().toLowerCase().contains(filterText);
    }

    private int getInteger(String string) {
        try {
            return Integer.parseInt(string);
        }
        catch (NumberFormatException ex) {
            return 0;
        }
    }




}
