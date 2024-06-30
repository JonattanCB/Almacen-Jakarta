package org.Almacen.TopAlmacen.Services;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.Almacen.TopAlmacen.DAO.ITipoUnidadDao;
import org.Almacen.TopAlmacen.DTO.TipoUnidad.TipoUnidadDto;
import org.Almacen.TopAlmacen.Model.TipoUnidad;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
@LocalBean
public class TipoUnidadService implements Serializable {

    @Inject
    private ITipoUnidadDao iTipoUnidadDao;

    @Transactional
    public List<TipoUnidadDto> getAllTipoUnidad() {
        List<TipoUnidad> tipoUnidadList =iTipoUnidadDao.getAll();
        return tipoUnidadList.stream()
                .map(c -> new TipoUnidadDto(c.getId(), c.getNombre(), c.getAbrev()))
                .collect(Collectors.toList());
    }

    @Transactional
    public  TipoUnidadDto getTipoUnidad(int id){
        var tipoUnidad = iTipoUnidadDao.getById(id);
        return new TipoUnidadDto(tipoUnidad.getId(), tipoUnidad.getNombre(), tipoUnidad.getAbrev());
    }

}
