package org.Almacen.TopAlmacen.Services;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.Almacen.TopAlmacen.DAO.IPrecioPorTipoUnidadDao;
import org.Almacen.TopAlmacen.DAO.ITipoUnidadDao;
import org.Almacen.TopAlmacen.DTO.TipoUnidad.TipoUnidadDto;
import org.Almacen.TopAlmacen.Mappers.TipoUnidadMapper;
import org.Almacen.TopAlmacen.Model.TipoUnidad;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
@LocalBean
public class TipoUnidadService implements Serializable {

    @Inject
    private ITipoUnidadDao iTipoUnidadDao;
    @Inject
    private IPrecioPorTipoUnidadDao iPrecioPorTipoUnidadDao;

    @Transactional
    public List<TipoUnidadDto> getAllTipoUnidad() {
        List<TipoUnidad> tipoUnidadList = iTipoUnidadDao.getAll();
        return tipoUnidadList.stream()
                .map(c -> new TipoUnidadDto(c.getId(), c.getNombre(), c.getAbrev()))
                .collect(Collectors.toList());
    }

    @Transactional
    public List<TipoUnidadDto> filterTipoUnidadList(int productoId) {
        List<Integer> registeredIds = iPrecioPorTipoUnidadDao.getAllbyProducto(productoId);
        List<TipoUnidad> tipoUnidadList = iTipoUnidadDao.getAll();

        return tipoUnidadList.stream()
                .filter(tipoUnidad -> !registeredIds.contains(tipoUnidad.getId()))
                .map(TipoUnidadMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<TipoUnidadDto> filterTipoUnidadListProducto(int productoId) {
        List<Integer> registeredIds = iPrecioPorTipoUnidadDao.getAllbyProducto(productoId);
        List<TipoUnidad> tipoUnidadList = iTipoUnidadDao.getAll();

        return tipoUnidadList.stream()
                .filter(tipoUnidad -> registeredIds.contains(tipoUnidad.getId()))
                .map(TipoUnidadMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public TipoUnidadDto getTipoUnidad(int id) {
        var tipoUnidad = iTipoUnidadDao.getById(id);
        return new TipoUnidadDto(tipoUnidad.getId(), tipoUnidad.getNombre(), tipoUnidad.getAbrev());
    }
}
