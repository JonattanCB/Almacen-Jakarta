package org.Almacen.TopAlmacen.Services;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.Almacen.TopAlmacen.DAO.IRolDao;
import org.Almacen.TopAlmacen.DTO.Rol.CreateRolDto;
import org.Almacen.TopAlmacen.DTO.Rol.RolDto;
import org.Almacen.TopAlmacen.DTO.Rol.UpdateRolDto;
import org.Almacen.TopAlmacen.Mappers.RolMapper;
import org.Almacen.TopAlmacen.Model.Rol;

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
                .map(c -> new RolDto(c.getId(), c.getNombre(), c.getEstado(), c.getFechaRegistro()))
                .collect(Collectors.toList());
    }

    @Transactional
    public List<RolDto> getAllRolActiva() {
        List<Rol> rols = iRolDao.getAllByEstadoActivoRols();
        return rols.stream()
                .map(c -> new RolDto(c.getId(), c.getNombre(), c.getEstado(), c.getFechaRegistro()))
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
