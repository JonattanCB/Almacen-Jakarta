package org.Almacen.Siman.Services;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.Almacen.Siman.DAO.IDependenciaDao;
import org.Almacen.Siman.DTO.Dependencia.CreateDependenciaDto;
import org.Almacen.Siman.DTO.Dependencia.DependenciaConUnidadesDto;
import org.Almacen.Siman.DTO.Dependencia.DependenciaDto;
import org.Almacen.Siman.DTO.Dependencia.UpdateDependenciaDto;
import org.Almacen.Siman.Mappers.DependenciaMapper;
import org.Almacen.Siman.Model.Dependencia;

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
    public List<DependenciaDto> getAllActivos() {
        var dependencias = iDependenciaDao.getAllActivos();
        return dependencias.stream().map(DependenciaMapper::toDto).collect(Collectors.toList());
    }

    @Transactional
    public DependenciaConUnidadesDto getByIdAllUnidades(int id) {
        var dependencias = iDependenciaDao.getById(id);
        return new DependenciaConUnidadesDto(dependencias.getId(), dependencias.getNombre(), dependencias.getEstado(), dependencias.getFechaRegistro(), dependencias.getUnidades());
    }

    @Transactional
    public  DependenciaDto getByIdDependencia(int id) {
        var dependencia = iDependenciaDao.getByIdDependencia(id);
        return DependenciaMapper.toDto(dependencia);
    }

    @Transactional
    public Dependencia create(CreateDependenciaDto createDependenciaDto) {
        var dependencia = DependenciaMapper.toDependenciaFromCreate(createDependenciaDto);
        return iDependenciaDao.create(dependencia);
    }

    @Transactional
    public Dependencia update(UpdateDependenciaDto updateDependenciaDto, int id) {
        var dependencia = DependenciaMapper.toDependenciaFromUpdate(updateDependenciaDto);
        return iDependenciaDao.update(dependencia,  id);
    }

    @Transactional
    public void cambioEstado(String estado, int  id) {
        iDependenciaDao.cambioestado(estado, id);
    }
}