package org.Almacen.TopAlmacen.Services;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.Almacen.TopAlmacen.DAO.*;
import org.Almacen.TopAlmacen.DTO.ComprobanteSalida.CreateComprobanteSalidaDto;
import org.Almacen.TopAlmacen.DTO.DetalleComprobanteSalida.CreateDetalleComprobanteSalidaDto;
import org.Almacen.TopAlmacen.DTO.DetalleComprobanteSalida.PdfDetallesComprobanteSalidaDto;
import org.Almacen.TopAlmacen.Mappers.ComprobanteSalidaMapper;
import org.Almacen.TopAlmacen.Mappers.DetalleComprobanteSalidaMapper;
import org.Almacen.TopAlmacen.Mappers.ProductoMapper;
import org.Almacen.TopAlmacen.Model.*;
import org.codehaus.groovy.tools.StringHelper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    @Inject
    private IRequerimiento_ComprobanteSDao iRequerimiento_ComprobanteSDao;
    @Inject
    private RequerimientoService requerimientoService;

    @Transactional
    public List<ComprobanteSalida> getall() {
        return iComprobanteSalidaDao.getAll();
    }

    @Transactional
    public ComprobanteSalida getById(String id) {
        return iComprobanteSalidaDao.getById(id);
    }

    @Transactional
    public ComprobanteSalida create(CreateComprobanteSalidaDto dto, List<CreateDetalleComprobanteSalidaDto> detallesDto) {
        System.out.println("aca1");
        var newComprobante = ComprobanteSalidaMapper.fromCreateDto(dto);
        var c = iComprobanteSalidaDao.create(newComprobante);
        for (CreateDetalleComprobanteSalidaDto detalleDto : detallesDto) {
            detalleDto.setComprobanteSalida(c);
            var itemComprobante = DetalleComprobanteSalidaMapper.fromCreate(detalleDto);
            var pptu = detalleDto.getPrecioPorTipoUnidad();
            var cantidadARestar = detalleDto.getCantidad() * pptu.getUnidadesPorTipoUnidadDeProducto();

            if (stockUnidadesService.checkStock(pptu.getProducto().getId(), cantidadARestar, pptu)) {
                iComprobanteSalidaDao.delete(c.getId());
                return null;
            } else {
                iDetalleComprobanteSalidaDao.create(itemComprobante);
                return c;
            }
        }
        return newComprobante;
    }

    @Transactional
    public void insertToBD(ComprobanteSalida c, List<DetalleComprobanteSalida> detalles, Requerimiento req) {
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
        var reqSalida = new Requerimiento_ComprobanteS();
        reqSalida.setComprobanteSalida(c);
        reqSalida.setRequerimiento(req);
        iRequerimiento_ComprobanteSDao.add(reqSalida);
        iComprobanteSalidaDao.setEstado("FINALIZADO",c.getId());
    }

    @Transactional
    public List<PdfDetallesComprobanteSalidaDto> getAlltToPdf(String codeRequerimeinto, String codeComprobanteSalida){
        List<PdfDetallesComprobanteSalidaDto> ls = new ArrayList<>();
        List<ItemsRequerimiento> requerimientoList = requerimientoService.getItemsByRequerimientoId(codeRequerimeinto);
        List<DetalleComprobanteSalida> detalles = getDetalleComprobanteSalida(codeComprobanteSalida);

        ls = detalles.stream()
                .flatMap(detalle -> requerimientoList.stream()
                        .filter(item -> item.getProducto().equals(detalle.getProducto())
                                && item.getTipoUnidad().equals(detalle.getTipoUnidad()))
                        .map(item -> {
                            PdfDetallesComprobanteSalidaDto dto = new PdfDetallesComprobanteSalidaDto();
                            dto.setItem_s(detalle.getProducto().getId());
                            dto.setCantidad_s(item.getCantidad());
                            dto.setMedida_s(item.getTipoUnidad().getAbrev());
                            dto.setDescripcion_s(ProductoMapper.toConcatProduct(item.getProducto()));
                            dto.setCodigo_d(String.valueOf(detalle.getProducto().getId()));
                            dto.setCant_d(detalle.getCantidad());
                            dto.setMedida_d(detalle.getTipoUnidad().getAbrev());
                            dto.setUnidad_v(detalle.getPrecioUnitario());
                            dto.setTotal_v(detalle.getPrecioTotal());
                            return dto;
                        }))
                .collect(Collectors.toList());
        return ls;
    }


    @Transactional
    public  void changeEstadoDesaprobado(String id) {
        iComprobanteSalidaDao.setEstado("DESAPROBADO", id);
    }

    @Transactional
    public List<DetalleComprobanteSalida> getDetalleComprobanteSalida(String id) {
        return iDetalleComprobanteSalidaDao.getAllbyComprobateSalida(id);
    }

}
