package org.Almacen.TopAlmacen.Services;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.Almacen.TopAlmacen.DAO.IMovimientoStockDao;
import org.Almacen.TopAlmacen.DTO.MovimientoStock.MovimientoStockDto;
import org.Almacen.TopAlmacen.Mappers.MovimientoStockMapper;
import org.Almacen.TopAlmacen.Model.MovimientoStock;

import java.util.List;
import java.util.stream.Collectors;

@Stateless
@LocalBean
public class MovimientoStockService {

    @Inject
    private IMovimientoStockDao iMovimientoStockDao;

    @Transactional
    public List<MovimientoStockDto> getAllMovimientoStock() {
        var movimiento = iMovimientoStockDao.getAll();
        return movimiento.stream().map(MovimientoStockMapper::toDto).collect(Collectors.toList());
    }

    @Transactional
    public MovimientoStockDto getMovimientoStockById(int id) {
        var movimiento = iMovimientoStockDao.getById(id);
        return MovimientoStockMapper.toDto(movimiento);
    }
}
