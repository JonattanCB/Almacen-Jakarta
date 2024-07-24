package org.Almacen.Siman.Services;

import jakarta.ejb.Local;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
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

        List<MovimientoStock> movimientos = iMovimientoStockDao.findMovimientosByProductoAndFechaRange(productoId, startDateTime, endDateTime);
        List<HistorialPrecios> historiales = iHistorialPreciosDao.findHistorialByProductoAndFechaRange(productoId, startDateTime, endDateTime);
        for (MovimientoStock mv : movimientos) {
            System.out.println(mv.getProducto().getNombre());
        }
        for (HistorialPrecios hp : historiales) {
            System.out.println(hp.getResponsable().getApellidos());
        }
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

        for (EventoKardex evento : eventos) {
            ItemKardexTemp item = new ItemKardexTemp();
            item.setFecha(evento.getFecha());
            System.out.println("Evento fecha: " + evento.getFecha());

            if (evento.getTipo().equals("Movimiento")) {
                MovimientoStock movimiento = (MovimientoStock) evento.getEvento();
                System.out.println("Movimiento: " + movimiento.getTipoMovimiento() + " - Cantidad: " + movimiento.getCantidad());

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
                HistorialPrecios precioActual = iHistorialPreciosDao.obtenerUltimoPrecioAntesDeFecha(productoId, evento.getFecha());
                if (precioActual != null) {
                    item.setCostoUni(precioActual.getPrecioRegistro());
                    System.out.println("Costo unitario: " + precioActual.getPrecioRegistro());
                } else {
                    item.setCostoUni(0.0);
                }
            } else if (evento.getTipo().equals("Precio")) {
                HistorialPrecios historial = (HistorialPrecios) evento.getEvento();
                System.out.println("Cambio de precio: " + historial.getPrecioRegistro());

                item.setArea("Almacén");
                item.setSolicitanteResponsable(historial.getResponsable().getNombres() + " " + historial.getResponsable().getApellidos());
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
        var obj = iHistorialStockDao.obtenerUltimoStockAntesDeFecha(productoId, startDate);
        System.out.println(obj.getCantidadStock()+"ENCONTRADO");
        return obj.getCantidadStock();
    }
}
