package org.Almacen.TopAlmacen.Services;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.Almacen.TopAlmacen.DAO.IUnidadDependenciaDao;
import org.Almacen.TopAlmacen.DTO.Categoria.CreateCategoriaDto;
import org.Almacen.TopAlmacen.DTO.Categoria.UpdateCategoriaDto;
import org.Almacen.TopAlmacen.DTO.UnidadDependencia.CreateUnidadDependenciaDto;
import org.Almacen.TopAlmacen.DTO.UnidadDependencia.UnidadDependenciaDto;
import org.Almacen.TopAlmacen.DTO.UnidadDependencia.UpdateUnidadDependenciaDto;
import org.Almacen.TopAlmacen.Mappers.CategoriaMapper;
import org.Almacen.TopAlmacen.Mappers.UnidadDependenciaMapper;
import org.Almacen.TopAlmacen.Model.Categoria;
import org.Almacen.TopAlmacen.Model.UnidadDependencia;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
@LocalBean
public class UnidadService implements Serializable {

    @Inject
    private IUnidadDependenciaDao iUnidadDependenciaDao;

    @Transactional
    public List<UnidadDependenciaDto> getAll() {
        var unidadDependencias = iUnidadDependenciaDao.getAll();
        return unidadDependencias.stream().map(UnidadDependenciaMapper::toDto).collect(Collectors.toList());
    }

    @Transactional
    public UnidadDependenciaDto getById(int id) {
        return UnidadDependenciaMapper.toDto(iUnidadDependenciaDao.getById(id));
    }


    @Transactional
    public UnidadDependencia createUnidad(CreateUnidadDependenciaDto createUnidadDependencia) {
        var unidad = UnidadDependenciaMapper.toUnidadDependenciaFromCreate(createUnidadDependencia);
        return iUnidadDependenciaDao.create(unidad);
    }

    @Transactional
    public UnidadDependencia updateCategoria(int id, UpdateUnidadDependenciaDto updateUnidadDependenciaDto ) {
        var unidad = UnidadDependenciaMapper.toUnidadDependenciaFromUpdate(updateUnidadDependenciaDto);
        return  iUnidadDependenciaDao.update(unidad,id);
    }

    @Transactional
    public void deleteUnidadDependencia(int id){
        iUnidadDependenciaDao.delete(id);
    }

}
