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

    @Transactional
    public List<CategoriaDto> getAllCategorias() {
        List<Categoria> categorias = iCategoriaDao.getAll();
        return categorias.stream()
                .map(c -> new CategoriaDto(c.getId(), c.getNombre(), c.getDescripcion(), String.valueOf(c.getEstado()), c.getFechaRegistro()))
                .collect(Collectors.toList());
    }


    @Transactional
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

    @Transactional
    public Categoria createCategoria(CreateCategoriaDto createCategoriaDto) {
        var categoria = CategoriaMapper.toCategoriaFromCreate(createCategoriaDto);
        return iCategoriaDao.create(categoria);
    }

    @Transactional
    public Categoria updateCategoria(int id, UpdateCategoriaDto updateCategoriaDto) {
        return iCategoriaDao.update(updateCategoriaDto, id);
    }

    @Transactional
    public Categoria deleteCategoria(int id) {
        return iCategoriaDao.delete(id);
    }
}
