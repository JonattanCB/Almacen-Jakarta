package org.Almacen.Siman.Services;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.Almacen.Siman.DAO.ICategoriaDao;
import org.Almacen.Siman.DTO.Categoria.CategoriaConProductosDto;
import org.Almacen.Siman.DTO.Categoria.CategoriaDto;
import org.Almacen.Siman.DTO.Categoria.CreateCategoriaDto;
import org.Almacen.Siman.DTO.Categoria.UpdateCategoriaDto;
import org.Almacen.Siman.Mappers.CategoriaMapper;
import org.Almacen.Siman.Model.Categoria;

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
        return categorias.stream().map(CategoriaMapper::toDto).collect(Collectors.toList());
    }


    @Transactional
    public CategoriaDto getCategoria(int id) {
        var categoria = iCategoriaDao.getById(id);
        return CategoriaMapper.toDto(categoria);
    }

    @Transactional
    public CategoriaConProductosDto getCategoriaById(int id) {
        var categoria = iCategoriaDao.getById(id);
        return new CategoriaConProductosDto(categoria.getId(), categoria.getNombre(), categoria.getDescripcion(), categoria.getEstado(), categoria.getFechaRegistro(), categoria.getProductos());
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
    public void cambioEstado(int id, String estado) {
        iCategoriaDao.cambioEstado(id, estado);
    }

    @Transactional
    public List<CategoriaDto> getAllCategoriasActivas() {
        List<Categoria> categorias = iCategoriaDao.getAllByEstadoActivo();
        return categorias.stream().map(c -> new CategoriaDto(c.getId(), c.getNombre(), c.getDescripcion(), String.valueOf(c.getEstado()), c.getFechaRegistro())).collect(Collectors.toList());
    }

    @Transactional
    public Categoria deleteCategoria(int id) {
        return iCategoriaDao.delete(id);
    }
}
