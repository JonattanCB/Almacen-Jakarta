package org.Almacen.Siman.Services;

import java.time.format.DateTimeFormatter;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.NoResultException;
import jakarta.transaction.Transactional;
import org.Almacen.Siman.DAO.IHistorialPreciosDao;
import org.Almacen.Siman.DAO.IHistorialStockDao;
import org.Almacen.Siman.DAO.IMovimientoStockDao;
import org.Almacen.Siman.DAO.IProductoDao;
import org.Almacen.Siman.Mappers.UsuarioMapper;
import org.Almacen.Siman.Model.HistorialPrecios;
import org.Almacen.Siman.Model.MovimientoStock;
import org.Almacen.Siman.Util.EventoKardex;
import org.Almacen.Siman.Util.ItemKardexTemp;
import org.Almacen.Siman.Util.KardexTemp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


@LocalBean
@Stateless
public class KardexService {
    @Inject
    private IHistorialPreciosDao iHistorialPreciosDao;
    @Inject
    private IMovimientoStockDao iMovimientoStockDao;
    @Inject
    private IProductoDao iProductoDao;
    @Inject
    private IHistorialStockDao iHistorialStockDao;

    @Transactional
    public List<EventoKardex> combinarYOrdenarEventos(int productoId, LocalDate startDate, LocalDate endDate) {
        LocalDateTime startDateTime = startDate.atStartOfDay();
        LocalDateTime endDateTime = endDate.plusDays(1).atStartOfDay().minusNanos(1);

        var movimientos = iMovimientoStockDao.findMovimientosByProductoAndFechaRange(productoId, startDateTime, endDateTime);
        var historiales = iHistorialPreciosDao.findHistorialByProductoAndFechaRange(productoId, startDateTime, endDateTime);

        var eventos = new ArrayList<EventoKardex>();
        for (MovimientoStock movimiento : movimientos) {
            LocalDateTime fechaSinNanos = movimiento.getFechaRegistro().withNano(0);
            eventos.add(new EventoKardex(fechaSinNanos, "Movimiento", movimiento));
        }

        for (HistorialPrecios historial : historiales) {
            LocalDateTime fechaSinNanos = historial.getFechaRegistro().withNano(0);
            eventos.add(new EventoKardex(fechaSinNanos, "Precio", historial));
        }

        eventos.sort(Comparator.comparing(EventoKardex::getFecha));

        return eventos;
    }


    @Transactional
    public KardexTemp generarKardex(int productoId, LocalDate startDate, LocalDate endDate) {
        System.out.println("Generando kardex para el producto ID: " + productoId);
        System.out.println("Rango de fechas: " + startDate + " - " + endDate);

        List<EventoKardex> eventos = combinarYOrdenarEventos(productoId, startDate, endDate);
        KardexTemp kardex = new KardexTemp();

        var producto = iProductoDao.getById(productoId);
        System.out.println("Producto: " + producto.getNombre());

        kardex.setProducto(producto.getNombre());
        kardex.setPeriodo(startDate.toString() + " - " + endDate.toString());

        var inventarioInicial = obtenerInventarioInicial(productoId, startDate.atStartOfDay());
        System.out.println("Inventario inicial: " + inventarioInicial);
        kardex.setInvInicial(inventarioInicial);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        for (EventoKardex evento : eventos) {
            ItemKardexTemp item = new ItemKardexTemp();
            item.setFecha(evento.getFecha().format(formatter)); // Formatear fecha

            if (evento.getTipo().equals("Movimiento")) {
                MovimientoStock movimiento = (MovimientoStock) evento.getEvento();
                item.setArea(movimiento.getDependencia().getNombre());
                item.setSolicitanteResponsable(UsuarioMapper.toConcatuser(movimiento.getSolicitante_Responsable()));
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
                HistorialPrecios precioActual = iHistorialPreciosDao.obtenerUltimoPrecioAntesDeFecha(productoId, evento.getFecha());
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

        return kardex;
    }

    private double obtenerInventarioInicial(int productoId, LocalDateTime startDate) {
        try {
            var obj = iHistorialStockDao.obtenerUltimoStockAntesDeFecha(productoId, startDate);
            return obj != null ? obj.getCantidadStock() : 0.0;
        } catch (NoResultException e) {
            return 0.0;
        }
    }


}
