package org.Almacen.Siman.Services;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.Almacen.Siman.DAO.IHistorialPreciosDao;
import org.Almacen.Siman.DTO.HistorialPrecios.HistorialPreciosDto;
import org.Almacen.Siman.Mappers.HistorialPreciosMapper;

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
