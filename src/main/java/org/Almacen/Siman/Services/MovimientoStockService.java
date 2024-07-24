package org.Almacen.Siman.Services;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.Almacen.Siman.DAO.IMovimientoStockDao;
import org.Almacen.Siman.DTO.MovimientoStock.CreateMovimientoStockDto;
import org.Almacen.Siman.DTO.MovimientoStock.MovimientoStockDto;
import org.Almacen.Siman.Mappers.MovimientoStockMapper;
import org.Almacen.Siman.Model.MovimientoStock;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
@LocalBean
public class MovimientoStockService implements Serializable {

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

    @Transactional
    public MovimientoStock create(CreateMovimientoStockDto dto) {
        var mov = MovimientoStockMapper.fromCrate(dto);
        iMovimientoStockDao.create(mov);
        return mov;
    }

    }
