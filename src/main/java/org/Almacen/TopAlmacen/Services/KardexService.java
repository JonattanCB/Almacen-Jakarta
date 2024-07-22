package org.Almacen.TopAlmacen.Services;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.Almacen.TopAlmacen.DAO.IHistorialPreciosDao;
import org.Almacen.TopAlmacen.DAO.IHistorialStockDao;
import org.Almacen.TopAlmacen.DAO.IMovimientoStockDao;
import org.Almacen.TopAlmacen.DAO.IProductoDao;
import org.Almacen.TopAlmacen.Mappers.UsuarioMapper;
import org.Almacen.TopAlmacen.Model.HistorialPrecios;
import org.Almacen.TopAlmacen.Model.MovimientoStock;
import org.Almacen.TopAlmacen.Util.EventoKardex;
import org.Almacen.TopAlmacen.Util.ItemKardexTemp;
import org.Almacen.TopAlmacen.Util.KardexTemp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


@LocalBean
@Stateless
public class KardexService {
    @Inject
    private IHistorialPreciosDao iHistorialPreciosDao;
    @Inject
    private IHistorialStockDao iHistorialStockDao;
    @Inject
    private IProductoDao iProductoDao;
    @Inject
    private IMovimientoStockDao iMovimientoStockDao;

    @Transactional
    public List<HistorialPrecios> obtenerHistorialPrecios(int productoId, LocalDate fechaInicio, LocalDate fechaFin) {
        List<HistorialPrecios> historialCompleto = new ArrayList<>();
        HistorialPrecios precioInicial = iHistorialPreciosDao.obtenerUltimoPrecioAntesDeFecha(productoId, fechaInicio);
        if (precioInicial != null) {
            historialCompleto.add(precioInicial);
        }
        List<HistorialPrecios> cambiosEnRango = iHistorialPreciosDao.findHistorialByProductoAndFechaRange(productoId, fechaInicio, fechaFin);
        historialCompleto.addAll(cambiosEnRango);
        return historialCompleto;
    }

    @Transactional
    public List<EventoKardex> combinarYOrdenarEventos(int productoId, LocalDate startDate, LocalDate endDate) {
        List<MovimientoStock> movimientos = iMovimientoStockDao.findMovimientosByProductoAndFechaRange(productoId, startDate, endDate);
        List<HistorialPrecios> historiales = obtenerHistorialPrecios(productoId, startDate, endDate);

        List<EventoKardex> eventos = new ArrayList<>();
        for (MovimientoStock movimiento : movimientos) {
            eventos.add(new EventoKardex(movimiento.getFechaRegistro(), "Movimiento", movimiento));
        }
        for (HistorialPrecios historial : historiales) {
            eventos.add(new EventoKardex(historial.getFechaRegistro(), "Precio", historial));
        }
        eventos.sort(Comparator.comparing(EventoKardex::getFecha));
        return eventos;
    }

    public List<KardexTemp> generarKardex(int productoId, LocalDate startDate, LocalDate endDate) {
        List<EventoKardex> eventos = combinarYOrdenarEventos(productoId, startDate, endDate);
        List<KardexTemp> kardexList = new ArrayList<>();
        KardexTemp kardex = new KardexTemp();

        var producto = iProductoDao.getById(productoId);
        kardex.setProducto(producto.getNombre());
        kardex.setPeriodo(startDate.toString() + " - " + endDate.toString());

        var inventarioInicial = obtenerInventarioInicial(productoId, startDate);
        kardex.setInvInicial(inventarioInicial);

        for (EventoKardex evento : eventos) {
            ItemKardexTemp item = new ItemKardexTemp();
            item.setFecha(evento.getFecha());

            if (evento.getTipo().equals("Movimiento")) {
                MovimientoStock movimiento = (MovimientoStock) evento.getEvento();
                item.setArea(movimiento.getDependencia().getNombre());
                item.setSolicitanteResponsable(movimiento.getSolicitante_Responsable().getNombres() + " " + movimiento.getSolicitante_Responsable().getApellidos());
                item.setInvInicial(inventarioInicial);

                if (movimiento.getTipoMovimiento().equals("ENTRADA")) {
                    item.setStockEntrada(movimiento.getCantidad());
                    item.setStockSalida(0);
                    inventarioInicial += movimiento.getCantidad();
                } else {
                    item.setStockEntrada(0);
                    item.setStockSalida(movimiento.getCantidad());
                    inventarioInicial -= movimiento.getCantidad();
                }
                HistorialPrecios precioActual = iHistorialPreciosDao.obtenerUltimoPrecioAntesDeFecha(productoId, evento.getFecha().toLocalDate());
                item.setCostoUni(precioActual.getPrecioRegistro());
            } else if (evento.getTipo().equals("Precio")) {
                HistorialPrecios historial = (HistorialPrecios) evento.getEvento();
                item.setArea("Almacén");
                item.setSolicitanteResponsable(UsuarioMapper.toConcatuser(historial.getResponsable()));
                item.setInvInicial(inventarioInicial);
                item.setStockEntrada(0);
                item.setStockSalida(0);
                item.setCostoUni(historial.getPrecioRegistro());
            }
            item.setInvFinal(inventarioInicial);
            kardex.getItems().add(item);
        }
        kardexList.add(kardex);
        return kardexList;
    }

    private double obtenerInventarioInicial(int productoId, LocalDate startDate) {
        var obj = iHistorialStockDao.obtenerUltimoStockAntesDeFecha(productoId, startDate);
        return obj.getCantidadStock();
    }
}
