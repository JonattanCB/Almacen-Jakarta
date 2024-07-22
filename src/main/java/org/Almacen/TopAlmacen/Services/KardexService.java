package org.Almacen.TopAlmacen.Services;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.Almacen.TopAlmacen.DAO.IHistorialPreciosDao;
import org.Almacen.TopAlmacen.DAO.IHistorialStockDao;
import org.Almacen.TopAlmacen.DAO.IMovimientoStockDao;
import org.Almacen.TopAlmacen.DAO.IProductoDao;
import org.Almacen.TopAlmacen.Mappers.ProductoMapper;
import org.Almacen.TopAlmacen.Model.HistorialPrecios;
import org.Almacen.TopAlmacen.Model.MovimientoStock;
import org.Almacen.TopAlmacen.Util.EventoKardex;
import org.Almacen.TopAlmacen.Util.ItemKardexTemp;
import org.Almacen.TopAlmacen.Util.KardexTemp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import static org.Almacen.TopAlmacen.Util.DateConverter.convertToLocalDateTimeViaInstant;

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

        // Obtener el último precio antes de la fecha de inicio del rango
        HistorialPrecios precioInicial = iHistorialPreciosDao.obtenerUltimoPrecioAntesDeFecha(productoId, fechaInicio);
        if (precioInicial != null) {
            historialCompleto.add(precioInicial);
        }

        // Obtener los cambios de precio dentro del rango
        List<HistorialPrecios> cambiosEnRango = iHistorialPreciosDao.findHistorialByProductoAndFechaRange(productoId, fechaInicio, fechaFin);
        historialCompleto.addAll(cambiosEnRango);

        return historialCompleto;
    }

    @Transactional
    public List<EventoKardex> combinarYOrdenarEventos(int productoId, LocalDate startDate, LocalDate endDate) {
        List<MovimientoStock> movimientos = iMovimientoStockDao.findMovimientosByProductoAndFechaRange(productoId, startDate, endDate);
        List<HistorialPrecios> historiales = iHistorialPreciosDao.findHistorialByProductoAndFechaRange(productoId, startDate, endDate);

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

   /* public List<KardexTemp> generarKardex(int productoId, LocalDate startDate, LocalDate endDate) {
        List<EventoKardex> eventos = combinarYOrdenarEventos(productoId, startDate, endDate);
        List<KardexTemp> kardexList = new ArrayList<>();
        KardexTemp kardex = new KardexTemp();

        // Obtener datos del producto
        Producto producto = iProductoDao.getById(productoId);
        kardex.setProducto(producto.getNombre());
        kardex.setPeriodo(startDate.toString() + " - " + endDate.toString());

        double inventarioInicial = obtenerInventarioInicial(productoId, startDate);
        kardex.setInvInicial(inventarioInicial);

        for (EventoKardex evento : eventos) {
            ItemKardexTemp item = new ItemKardexTemp();
            item.setFecha(evento.getFecha());

            if (evento.getTipo().equals("Movimiento")) {
                MovimientoStock movimiento = (MovimientoStock) evento.getEvento();
                item.setArea("Almacén");
                item.setSolicitanteResponsable(movimiento.getSolicitante_Responsable().getNombreCompleto());
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
                item.setCostoUni(movimiento.getPrecio());
            } else if (evento.getTipo().equals("Precio")) {
                HistorialPrecios historial = (HistorialPrecios) evento.getEvento();
                item.setArea("Almacén");
                item.setSolicitanteResponsable(historial.getResponsable().getNombreCompleto());
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
        // Implementa la lógica para obtener el inventario inicial antes de la fecha de inicio
        // Podría ser una consulta adicional a la base de datos o una lógica preexistente
        return 0; // Placeholder: Cambia esta lógica según tus necesidades
    }*/
}
