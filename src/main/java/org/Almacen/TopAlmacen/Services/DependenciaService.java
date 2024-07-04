package org.Almacen.TopAlmacen.Services;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.Almacen.TopAlmacen.DAO.IDependenciaDao;
import org.Almacen.TopAlmacen.DTO.Dependencia.DependenciaConUnidadesDto;
import org.Almacen.TopAlmacen.DTO.Dependencia.DependenciaDto;
import org.Almacen.TopAlmacen.Mappers.DependenciaMapper;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
@LocalBean
public class DependenciaService {

    @Inject
    private IDependenciaDao iDependenciaDao;

    @Transactional
    public List<DependenciaDto> getAll() {
        var dependencias = iDependenciaDao.getAll();

        return dependencias.stream().map(DependenciaMapper::toDto).collect(Collectors.toList());
    }

    @Transactional
    public DependenciaConUnidadesDto getById(int id) {
        var dependencias = iDependenciaDao.getById(id);
        return new DependenciaConUnidadesDto(dependencias.getId(), dependencias.getNombre(), dependencias.getEstado(), dependencias.getFechaRegistro(), dependencias.getUnidades());

    }
}