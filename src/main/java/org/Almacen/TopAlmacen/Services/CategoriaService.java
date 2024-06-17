package org.Almacen.TopAlmacen.Services;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import org.Almacen.TopAlmacen.DAO.ICategoriaDao;
import org.Almacen.TopAlmacen.DTO.Categoria.CategoriaDto;
import org.Almacen.TopAlmacen.DTO.Categoria.CreateCategoriaDto;
import org.Almacen.TopAlmacen.DTO.Categoria.UpdateCategoriaDto;
import org.Almacen.TopAlmacen.Mappers.CategoriaMapper;
import org.Almacen.TopAlmacen.Model.Categoria;

import java.util.List;

@Stateless
public class CategoriaService {

    @Inject
    private ICategoriaDao iCategoriaDao;

    public List<CategoriaDto> getAllCategorias() {
        List<Categoria> categorias = iCategoriaDao.getAll();
        return CategoriaMapper.toDTOList(categorias);
    }

    public CategoriaDto getCategoriaById(int id) {
        var categoria = iCategoriaDao.getById(id);
        return CategoriaMapper.toDto(categoria);
    }

    public Categoria createCategoria(CreateCategoriaDto createCategoriaDto) {
        var categoria = CategoriaMapper.toCategoriaFromCreate(createCategoriaDto);
        iCategoriaDao.create(categoria);
        return categoria;
    }

    public void updateCategoria(int id, UpdateCategoriaDto updateCategoriaDto) {
        iCategoriaDao.update(updateCategoriaDto, id);
    }
    public void deleteCategoria(int id) {
        iCategoriaDao.delete(id);
    }
}


