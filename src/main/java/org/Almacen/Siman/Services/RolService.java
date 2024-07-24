package org.Almacen.Siman.Services;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.Almacen.Siman.DAO.IRolDao;
import org.Almacen.Siman.DTO.Rol.CreateRolDto;
import org.Almacen.Siman.DTO.Rol.RolDto;
import org.Almacen.Siman.DTO.Rol.UpdateRolDto;
import org.Almacen.Siman.Mappers.RolMapper;
import org.Almacen.Siman.Model.Rol;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
@LocalBean
public class RolService implements Serializable {

    @Inject
    private IRolDao iRolDao;


    @Transactional
    public List<RolDto> getAllRol() {
        List<Rol> rols = iRolDao.getAll();
        return rols.stream()
                .map(c -> new RolDto(c.getId(), c.getNombre(), c.getEstado(), c.getFechaRegistro(), c.getUnidadDependencia()))
                .collect(Collectors.toList());
    }

    @Transactional
    public List<RolDto> getAllRolActiva() {
        List<Rol> rols = iRolDao.getAllByEstadoActivoRols();
        return rols.stream()
                .map(c -> new RolDto(c.getId(), c.getNombre(), c.getEstado(), c.getFechaRegistro(), c.getUnidadDependencia()))
                .collect(Collectors.toList());
    }

    @Transactional
    public Rol createRol(CreateRolDto createRolDto) {
        var rol = RolMapper.toRolFromCreate(createRolDto);
        return iRolDao.create(rol);
    }

    @Transactional
    public RolDto getRolById(int id) {
        var rol = iRolDao.getById(id);
        return RolMapper.toRolDto(rol);
    }

    @Transactional
    public Rol updateRol(UpdateRolDto updateRolDto, int id) {
        return iRolDao.update(updateRolDto, id);
    }

    @Transactional
    public void cambiarEstado(int id, String estado) {
        iRolDao.cambioEstado(id, estado);
    }


}
