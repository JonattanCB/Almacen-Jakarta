package org.Almacen.TopAlmacen.Services;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.Almacen.TopAlmacen.DAO.IComprobanteSalidaDao;
import org.Almacen.TopAlmacen.DAO.IDetalleComprobanteSalidaDao;
import org.Almacen.TopAlmacen.DAO.IMovimientoStockDao;
import org.Almacen.TopAlmacen.DAO.IStockUnidadesDao;
import org.Almacen.TopAlmacen.DTO.ComprobanteSalida.CreateComprobanteSalidaDto;
import org.Almacen.TopAlmacen.DTO.DetalleComprobanteSalida.CreateDetalleComprobanteSalidaDto;
import org.Almacen.TopAlmacen.Mappers.ComprobanteSalidaMapper;
import org.Almacen.TopAlmacen.Mappers.DetalleComprobanteSalidaMapper;
import org.Almacen.TopAlmacen.Model.ComprobanteSalida;
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
        for (CreateDetalleComprobanteSalidaDto detalleDto : detallesDto) {
            var itemComprobante = DetalleComprobanteSalidaMapper.fromCreate(detalleDto);
            var pptu = detalleDto.getPrecioPorTipoUnidad();
            iDetalleComprobanteSalidaDao.create(itemComprobante);

            var cantidadARestar = detalleDto.getCantidad() * detalleDto.getPrecioPorTipoUnidad().getUnidadesPorTipoUnidadDeProducto();
            stockUnidadesService.subtractStockUnidades(pptu, cantidadARestar);

            var ms = new MovimientoStock();
            ms.setTipoMovimiento("SALIDA");
            ms.setProducto(detalleDto.getPrecioPorTipoUnidad().getProducto());
            ms.setCantidad(detalleDto.getCantidad());
            ms.setTipoUnidad(detalleDto.getPrecioPorTipoUnidad().getTipoUnidad().getAbrev());
            imovimientoStockDao.create(ms);

        }
        iComprobanteSalidaDao.create(newComprobante);
        return newComprobante;
    }


}
