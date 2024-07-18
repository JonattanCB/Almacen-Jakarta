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
import org.Almacen.TopAlmacen.Model.ComprobanteSalida;
import org.Almacen.TopAlmacen.Model.DetalleComprobanteSalida;
import org.Almacen.TopAlmacen.Model.MovimientoStock;

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
            detalleDto.setComprobanteSalida(c);
            var itemComprobante = DetalleComprobanteSalidaMapper.fromCreate(detalleDto);
            var pptu = detalleDto.getPrecioPorTipoUnidad();

            var cantidadARestar = detalleDto.getCantidad() * pptu.getUnidadesPorTipoUnidadDeProducto();
            System.out.println(pptu.getProducto().getNombre() + pptu.getProducto().getStockUnidades().getCantidadStockUnidad());

            if (stockUnidadesService.subtractStockUnidades(pptu, cantidadARestar) == null) {
                iComprobanteSalidaDao.delete(c.getId());
                return null;
            }
            iDetalleComprobanteSalidaDao.create(itemComprobante);

            var ms = new MovimientoStock();
            ms.setTipoMovimiento("SALIDA");
            ms.setProducto(itemComprobante.getProducto());
            ms.setCantidad(detalleDto.getCantidad());
            ms.setTipoUnidad(itemComprobante.getTipoUnidad().getAbrev());
            imovimientoStockDao.create(ms);

        }
        return newComprobante;
    }

    @Transactional
    public List<DetalleComprobanteSalida> getDetalleComprobanteSalida(int id) {
        return iDetalleComprobanteSalidaDao.getAllbyComprobateSalida(id);
    }

}
