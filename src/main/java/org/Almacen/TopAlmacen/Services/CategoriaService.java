package org.Almacen.TopAlmacen.Services;

import jakarta.ejb.Asynchronous;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.Almacen.TopAlmacen.DAO.ICategoriaDao;
import org.Almacen.TopAlmacen.DTO.Categoria.CategoriaConProductosDto;
import org.Almacen.TopAlmacen.DTO.Categoria.CategoriaDto;
import org.Almacen.TopAlmacen.DTO.Categoria.CreateCategoriaDto;
import org.Almacen.TopAlmacen.DTO.Categoria.UpdateCategoriaDto;
import org.Almacen.TopAlmacen.DTO.Producto.ProductoDto;
import org.Almacen.TopAlmacen.Mappers.CategoriaMapper;
import org.Almacen.TopAlmacen.Mappers.MarcaMapper;
import org.Almacen.TopAlmacen.Model.Categoria;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

@Stateless
@LocalBean
public class CategoriaService implements Serializable {

    @Inject
    private ICategoriaDao iCategoriaDao;

    @Asynchronous
    public Future<List<CategoriaDto>> getAllCategoriasAsync() {
        List<Categoria> categorias = iCategoriaDao.getAll();
        if (categorias == null || categorias.isEmpty()) {
            return CompletableFuture.completedFuture(null);
        }
        return CompletableFuture.completedFuture(categorias.stream()
                .map(c -> new CategoriaDto(c.getId(), c.getNombre(), c.getDescripcion(), c.getEstado(), c.getFechaRegistro()))
                .collect(Collectors.toList()));
    }

    @Asynchronous
    public Future<CategoriaConProductosDto> getCategoriaByIdAsync(int id) {
        return CompletableFuture.completedFuture(getCategoriaById(id));
    }

    public CategoriaConProductosDto getCategoriaById(int id) {
        var categoria = iCategoriaDao.getById(id);
        if (categoria == null || !categoria.getEstado().equals("Activo")) {
            return null;
        }
        List<ProductoDto> productosDto = categoria.getProductos().stream()
                .map(p -> new ProductoDto(p.getId(),
                        p.getNombre(),
                        p.getColor(),
                        p.getPeso(),
                        CategoriaMapper.toDto(p.getCategoria()),
                        MarcaMapper.toDto(p.getMarca()),
                        p.getFechaRegistro()
                ))
                .collect(Collectors.toList());

        return new CategoriaConProductosDto(
                categoria.getId(),
                categoria.getNombre(),
                categoria.getDescripcion(),
                categoria.getEstado(),
                categoria.getFechaRegistro(),
                productosDto
        );
    }

    @Asynchronous
    public void createCategoriaAsync(CreateCategoriaDto createCategoriaDto) {
        var categoria = CategoriaMapper.toCategoriaFromCreate(createCategoriaDto);
        iCategoriaDao.create(categoria);
    }

    @Asynchronous
    public void updateCategoriaAsync(int id, UpdateCategoriaDto updateCategoriaDto) {
        iCategoriaDao.update(updateCategoriaDto, id);
    }

    @Asynchronous
    public void deleteCategoriaAsync(int id) {
        iCategoriaDao.delete(id);
    }
}