package org.Almacen.TopAlmacen.Controladores;

import jakarta.annotation.ManagedBean;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.annotation.ViewMap;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.Almacen.TopAlmacen.DTO.Categoria.CategoriaDto;
import org.Almacen.TopAlmacen.DTO.Categoria.CreateCategoriaDto;
import org.Almacen.TopAlmacen.DTO.Categoria.UpdateCategoriaDto;
import org.Almacen.TopAlmacen.Model.Categoria;
import org.Almacen.TopAlmacen.Services.CategoriaService;

import java.io.Serializable;
import java.util.List;

@Data
@Named
@SessionScoped
public class CategoriaBeans implements Serializable {

    @Inject
    CategoriaService categoriaService;

    private CreateCategoriaDto nuevaCategoria = new CreateCategoriaDto();
    private UpdateCategoriaDto updateCategoria = new UpdateCategoriaDto();
    private List<CategoriaDto> categorias;
    private int categoriaId;

    @PostConstruct
    public void init() {
        loadCategorias();
    }

    public void loadCategorias() {
        System.out.println("Capa CategoriaBeans");
        categorias = categoriaService.getAllCategorias();
        if (categorias == null){
            System.out.println("lista vacia");
        }else{
            System.out.println("lista llena "+categorias.size());
        }
        for (CategoriaDto categoria : categorias) {
            System.out.println(categoria.getNombre());
        }
    }
    public void createCategoria() {
        nuevaCategoria.setEstado("Activo");
        nuevaCategoria.setNombre("Cate");
        nuevaCategoria.setDescripcion("ddescripcion");
        categoriaService.createCategoria(nuevaCategoria);
        loadCategorias();
        nuevaCategoria = new CreateCategoriaDto();
    }
    public void cargarCategoriaParaEdicion(int id) {
        var categoria=categoriaService.getCategoriaById(id);
        if (categoria!=null){
            updateCategoria.setNombre(categoria.getNombre());
            updateCategoria.setDescripcion(categoria.getDescripcion());
            updateCategoria.setEstado(categoria.getEstado());
            categoriaId=id;
        }
    }
    public void updateCategoria() {
        if (categoriaId!=0){
            categoriaService.updateCategoria(categoriaId, updateCategoria);
            loadCategorias();
        }
    }
    public void eliminarCategoria() {
        categoriaService.deleteCategoria(categoriaId);
        loadCategorias();
    }

}
