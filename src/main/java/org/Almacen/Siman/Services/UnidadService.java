package org.Almacen.Siman.Services;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.Almacen.Siman.DAO.IUnidadDependenciaDao;
import org.Almacen.Siman.DTO.UnidadDependencia.CreateUnidadDependenciaDto;
import org.Almacen.Siman.DTO.UnidadDependencia.UnidadDependenciaDto;
import org.Almacen.Siman.DTO.UnidadDependencia.UpdateUnidadDependenciaDto;
import org.Almacen.Siman.Mappers.UnidadDependenciaMapper;
import org.Almacen.Siman.Model.UnidadDependencia;

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
    public List<UnidadDependenciaDto> getAllByDependencia(int dependencia) {
        var unidadDependencias = iUnidadDependenciaDao.getAllByDependencia(dependencia);
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
