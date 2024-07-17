package org.Almacen.TopAlmacen.Services;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.Almacen.TopAlmacen.DAO.IComprobanteSalidaDao;
import org.Almacen.TopAlmacen.DAO.IDetalleComprobanteSalidaDao;
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

    @Transactional
    public List<ComprobanteSalida> getall() {
        return iComprobanteSalidaDao.getAll();
    }

    @Transactional
    public ComprobanteSalida get(int id) {
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

        }
        iComprobanteSalidaDao.create(newComprobante);
        return newComprobante;
    }


}
