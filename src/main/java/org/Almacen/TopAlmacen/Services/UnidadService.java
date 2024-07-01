package org.Almacen.TopAlmacen.Services;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.Almacen.TopAlmacen.DAO.IUnidadDependenciaDao;
import org.Almacen.TopAlmacen.DTO.UnidadDependencia.UnidadDependenciaDto;
import org.Almacen.TopAlmacen.Mappers.UnidadDependenciaMapper;
import org.Almacen.TopAlmacen.Model.UnidadDependencia;

import java.util.List;
import java.util.stream.Collectors;

@Stateless
@LocalBean
public class UnidadService {

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
}
