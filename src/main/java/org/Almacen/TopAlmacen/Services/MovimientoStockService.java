package org.Almacen.TopAlmacen.Services;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.Almacen.TopAlmacen.DAO.IHistorialPreciosDao;
import org.Almacen.TopAlmacen.DAO.IHistorialStockDao;
import org.Almacen.TopAlmacen.DAO.IMovimientoStockDao;
import org.Almacen.TopAlmacen.DAO.IProductoDao;
import org.Almacen.TopAlmacen.DTO.MovimientoStock.CreateMovimientoStockDto;
import org.Almacen.TopAlmacen.DTO.MovimientoStock.MovimientoStockDto;
import org.Almacen.TopAlmacen.Mappers.MovimientoStockMapper;
import org.Almacen.TopAlmacen.Mappers.ProductoMapper;
import org.Almacen.TopAlmacen.Model.MovimientoStock;
import org.Almacen.TopAlmacen.Model.Producto;
import org.Almacen.TopAlmacen.Util.ItemKardexTemp;
import org.Almacen.TopAlmacen.Util.KardexTemp;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
    @Inject
    private IProductoDao iProductoDao;

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
    public KardexTemp createKardex(List<Date> dateRange, int productoId) {
        if (dateRange != null && dateRange.size() == 2) {
            Date startDate = dateRange.get(0);
            Date endDate = dateRange.get(1);
            String concatDate = "{startDate}-{endDate}";

            var startDateTime = convertToLocalDateTimeViaInstant(startDate);
            var endDateTime = convertToLocalDateTimeViaInstant(endDate);

            var producto = iProductoDao.getById(productoId);

            var listMovimientos = iMovimientoStockDao.findMovimientosByProductoAndFechaRange(productoId, startDateTime, endDateTime);
            var listHisStock = iHistorialStockDao.findHistorialByProductoAndFechaRange(productoId, startDateTime, endDateTime);
            var listHisPrecios = iHistorialPreciosDao.findHistorialByProductoAndFechaRange(productoId, startDateTime, endDateTime);

            var kardex = new KardexTemp();
            kardex.setProducto(ProductoMapper.toConcatProduct(producto));
            kardex.setPeriodo(concatDate);
            kardex.setInvInicial(listHisStock.get(0).getCantidadStock());
            kardex.setUndMedida("UND");
            double invIni= kardex.getInvInicial();
            var itemsKardex = new ArrayList<ItemKardexTemp>();
            for (MovimientoStock mov : listMovimientos) {
                var item = new ItemKardexTemp();
                item.setFecha(mov.getFechaRegistro());
                item.setArea(mov.getDependencia().getNombre());
                item.setSolicitanteResponsable(mov.getSolicitante_Responsable().getNombres() + " " + mov.getSolicitante_Responsable().getApellidos() + " " + mov.getSolicitante_Responsable().getNombres());
                item.setInvInicial(invIni);
             //   item.setCostoUni(mov.getTipoMovimiento().equals("ENTRADA"))?
            }

            }
            return null;
        }
    }
