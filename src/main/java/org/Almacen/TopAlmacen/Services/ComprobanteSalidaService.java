package org.Almacen.TopAlmacen.Services;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.Almacen.TopAlmacen.DAO.*;
import org.Almacen.TopAlmacen.DTO.ComprobanteSalida.CreateComprobanteSalidaDto;
import org.Almacen.TopAlmacen.DTO.DetalleComprobanteSalida.CreateDetalleComprobanteSalidaDto;
import org.Almacen.TopAlmacen.Mappers.ComprobanteSalidaMapper;
import org.Almacen.TopAlmacen.Mappers.DetalleComprobanteSalidaMapper;
import org.Almacen.TopAlmacen.Model.*;

import java.io.Serializable;
import java.util.List;

@Stateless
@LocalBean
public class ComprobanteSalidaService implements Serializable {
    @Inject
    private IComprobanteSalidaDao iComprobanteSalidaDao;
    @Inject
    private IDetalleComprobanteSalidaDao iDetalleComprobanteSalidaDao;
    @Inject
    private IPrecioPorTipoUnidadDao iPrecioPorTipoUnidadDao;
    @Inject
    private StockUnidadesService stockUnidadesService;
    @Inject
    private IMovimientoStockDao imovimientoStockDao;
    @Inject
    private IHistorialStockDao iHistorialStockDao;

    @Transactional
    public List<ComprobanteSalida> getall() {
        return iComprobanteSalidaDao.getAll();
    }

    @Transactional
    public ComprobanteSalida getById(int id) {
        return iComprobanteSalidaDao.getById(id);
    }

    @Transactional
    public ComprobanteSalida create(CreateComprobanteSalidaDto dto, List<CreateDetalleComprobanteSalidaDto> detallesDto) {
        var newComprobante = ComprobanteSalidaMapper.fromCreateDto(dto);
        var c = iComprobanteSalidaDao.create(newComprobante);
        for (CreateDetalleComprobanteSalidaDto detalleDto : detallesDto) {
            var itemComprobante = DetalleComprobanteSalidaMapper.fromCreate(detalleDto);
            var pptu = detalleDto.getPrecioPorTipoUnidad();

            var cantidadARestar = detalleDto.getCantidad() * pptu.getUnidadesPorTipoUnidadDeProducto();
            System.out.println(pptu.getProducto().getNombre() + pptu.getProducto().getStockUnidades().getCantidadStockUnidad());
            if (stockUnidadesService.checkStock(pptu.getProducto().getId(), cantidadARestar, pptu)) {
                iDetalleComprobanteSalidaDao.create(itemComprobante);
                return c;
            } else {
                iComprobanteSalidaDao.delete(c.getId());
                return null;
            }
        }
        return newComprobante;
    }

    @Transactional
    public void insertToBD(ComprobanteSalida c, List<DetalleComprobanteSalida> detalles) {
        for (DetalleComprobanteSalida d : detalles) {
            var pptu = iPrecioPorTipoUnidadDao.getByIdProductoIdTipoUnidad(d.getProducto().getId(), d.getTipoUnidad().getId());

            var mov = new MovimientoStock();
            mov.setTipoMovimiento("SALIDA");
            mov.setCantidad(pptu.getUnidadesPorTipoUnidadDeProducto() * d.getCantidad());
            mov.setSolicitante_Responsable(c.getUsuario());

            mov.setProducto(d.getProducto());
            mov.setDependencia(c.getDependencia());
            imovimientoStockDao.create(mov);

            var hisStock = new HistorialStock();
            hisStock.setCantidadStock(d.getCantidad() * pptu.getUnidadesPorTipoUnidadDeProducto());
            hisStock.setStockUnidades(d.getProducto().getStockUnidades());
            iHistorialStockDao.add(hisStock);

            stockUnidadesService.subtractStockUnidades(pptu,d.getCantidad()*pptu.getUnidadesPorTipoUnidadDeProducto());

        }
        iComprobanteSalidaDao.setEstado("COMPLETADO",c.getId());
    }


    @Transactional
    public List<DetalleComprobanteSalida> getDetalleComprobanteSalida(int id) {
        return iDetalleComprobanteSalidaDao.getAllbyComprobateSalida(id);
    }

}
