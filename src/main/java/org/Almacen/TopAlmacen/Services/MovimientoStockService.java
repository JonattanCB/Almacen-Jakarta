package org.Almacen.TopAlmacen.Services;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.Almacen.TopAlmacen.DAO.IHistorialPreciosDao;
import org.Almacen.TopAlmacen.DAO.IHistorialStockDao;
import org.Almacen.TopAlmacen.DAO.IMovimientoStockDao;
import org.Almacen.TopAlmacen.DTO.MovimientoStock.CreateMovimientoStockDto;
import org.Almacen.TopAlmacen.DTO.MovimientoStock.MovimientoStockDto;
import org.Almacen.TopAlmacen.Mappers.MovimientoStockMapper;
import org.Almacen.TopAlmacen.Mappers.ProductoMapper;
import org.Almacen.TopAlmacen.Model.MovimientoStock;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.Almacen.TopAlmacen.Util.DateConverter.convertToLocalDateTimeViaInstant;

@Stateless
@LocalBean
public class MovimientoStockService implements Serializable {

    @Inject
    private IMovimientoStockDao iMovimientoStockDao;
    @Inject
    private IHistorialStockDao iHistorialStockDao;
    @Inject
    private IHistorialPreciosDao iHistorialPreciosDao;

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

    @Transactional
    public List<MovimientoStock> createKardex(List<Date> dateRange, int productoId) {
        if (dateRange != null && dateRange.size() == 2) {
            Date startDate = dateRange.get(0);
            Date endDate = dateRange.get(1);

            var startDateTime = convertToLocalDateTimeViaInstant(startDate);
            var endDateTime = convertToLocalDateTimeViaInstant(endDate);
            var listMovimientos = iMovimientoStockDao.findMovimientosByProductoAndFechaRange(productoId, startDateTime, endDateTime);

        }
        return null;
    }
}
