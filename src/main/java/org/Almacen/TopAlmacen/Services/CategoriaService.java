package org.Almacen.TopAlmacen.Services;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.Id;
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
import java.util.stream.Collectors;

@Stateless
@LocalBean
public class CategoriaService implements Serializable {

    @Inject
    private ICategoriaDao iCategoriaDao;

    @Transactional
    public List<CategoriaDto> getAllCategorias() {
        List<Categoria> categorias = iCategoriaDao.getAll();
        if (categorias == null || categorias.isEmpty()) {
            return null;
        }

        System.out.println("categoria service ejecutandose ");
        return categorias.stream()
                .map(c -> new CategoriaDto(c.getId(), c.getNombre(), c.getDescripcion(), String.valueOf(c.getEstado()), c.getFechaRegistro()))
                .collect(Collectors.toList());
    }

    @Transactional
    public CategoriaConProductosDto getCategoriaById(int id) {
        var categoria = iCategoriaDao.getById(id);
        if (categoria == null|| !categoria.getEstado().equals("Activo")) {
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

    public Categoria createCategoria(CreateCategoriaDto createCategoriaDto) {
        System.out.println("Datos recibidos en el servicio: " + createCategoriaDto.getNombre() + ", " + createCategoriaDto.getDescripcion());
        var categoria = CategoriaMapper.toCategoriaFromCreate(createCategoriaDto);
        iCategoriaDao.create(categoria);
        System.out.println("Creado en Services");
        return categoria;
    }

    public void updateCategoria(int id, UpdateCategoriaDto updateCategoriaDto) {
        iCategoriaDao.update(updateCategoriaDto, id);
    }

    public void deleteCategoria(int id) {
        iCategoriaDao.delete(id);
    }
}


