package org.Almacen.Siman.Services;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import org.Almacen.Siman.DAO.IAccesoDao;
import org.Almacen.Siman.DTO.Acceso.AccesoDto;
import org.Almacen.Siman.Mappers.AccesoMapper;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
@LocalBean
public class AccesoService implements Serializable {

    @Inject
    private IAccesoDao iAccesoDao;

    public List<AccesoDto> listarAccesos(int idRol) {
        var menulist = iAccesoDao.findByRolId(idRol);
        return menulist.stream().map(AccesoMapper::toDto).collect(Collectors.toList());
    }

    public AccesoDto getbyID(int id) {
        return  AccesoMapper.toDto(iAccesoDao.getById(id));
    }

}
