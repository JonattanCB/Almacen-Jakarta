package org.Almacen.TopAlmacen.Services;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.Almacen.TopAlmacen.DAO.IHistorialPreciosDao;
import org.Almacen.TopAlmacen.DTO.HistorialPrecios.HistorialPreciosDto;
import org.Almacen.TopAlmacen.Mappers.HistorialPreciosMapper;
import org.Almacen.TopAlmacen.Model.HistorialPrecios;

import java.util.List;
import java.util.stream.Collectors;

@Stateless
@LocalBean
public class HistorialPreciosService {

    @Inject
    private IHistorialPreciosDao iHistorialPreciosDao;

    @Transactional
    public List<HistorialPreciosDto> getAllHistorialPrecios() {
        var historiales = iHistorialPreciosDao.getAll();
        return historiales.stream().map(HistorialPreciosMapper::toDto).collect(Collectors.toList());
    }

    @Transactional
    public HistorialPreciosDto getHistorialPrecioById(int id) {
        var historial = iHistorialPreciosDao.getById(id);
        return HistorialPreciosMapper.toDto(historial);
    }
}
