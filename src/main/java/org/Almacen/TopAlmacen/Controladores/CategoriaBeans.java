package org.Almacen.TopAlmacen.Controladores;

import jakarta.annotation.ManagedBean;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.annotation.ViewMap;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import org.Almacen.TopAlmacen.DTO.Categoria.CategoriaDto;
import org.Almacen.TopAlmacen.DTO.Categoria.CreateCategoriaDto;
import org.Almacen.TopAlmacen.DTO.Categoria.UpdateCategoriaDto;
import org.Almacen.TopAlmacen.Model.Categoria;
import org.Almacen.TopAlmacen.Services.CategoriaService;
import org.primefaces.util.LangUtils;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Future;

@Data
@Named
@SessionScoped
public class CategoriaBeans implements Serializable {

    @Inject
    CategoriaService categoriaService;

    private CreateCategoriaDto nuevaCategoria = new CreateCategoriaDto();
    private UpdateCategoriaDto updateCategoria = new UpdateCategoriaDto();
    private List<CategoriaDto> categorias;
    private List<CategoriaDto> categoriasSeleccionadas;
    private int categoriaId;

    @PostConstruct
    public void init() {
        loadCategorias();
    }

    public void nuevaCategoria() {
        nuevaCategoria = new CreateCategoriaDto();
    }

    public  void

    public void loadCategorias() {
        try {
            var futureCategorias = categoriaService.getAllCategoriasAsync();
            categorias = futureCategorias.get();
            if (categorias == null) {
                System.out.println("lista vacia");
            }

            System.out.println("lista llena " + categorias.size());

            for (CategoriaDto categoria : categorias) {
                System.out.println(categoria.getNombre());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createCategoria() {
        System.out.println("entra aca");
        nuevaCategoria.setEstado("Activo");
        nuevaCategoria.setNombre("Cate");
        nuevaCategoria.setDescripcion("ddescripcion");
        categoriaService.createCategoriaAsync(nuevaCategoria);
        loadCategorias();
        nuevaCategoria = new CreateCategoriaDto();
    }

    public void cargarCategoriaParaEdicion(int id) {
        var categoria = categoriaService.getCategoriaById(id);
        if (categoria != null) {
            updateCategoria.setNombre(categoria.getNombre());
            updateCategoria.setDescripcion(categoria.getDescripcion());
            updateCategoria.setEstado(categoria.getEstado());
            categoriaId = id;
        }
    }

    public void updateCategoria() {
        if (categoriaId != 0) {
            categoriaService.updateCategoriaAsync(categoriaId, updateCategoria);
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
