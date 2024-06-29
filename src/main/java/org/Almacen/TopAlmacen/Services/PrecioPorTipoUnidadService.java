package org.Almacen.TopAlmacen.Services;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.Almacen.TopAlmacen.DAO.IPrecioPorTipoUnidadDao;
import org.Almacen.TopAlmacen.DTO.PrecioPorTipoUnidad.CreatePrecioPorTipoUnidadDto;
import org.Almacen.TopAlmacen.DTO.PrecioPorTipoUnidad.PrecioPorTipoUnidadDto;
import org.Almacen.TopAlmacen.DTO.PrecioPorTipoUnidad.UpdatePrecioPorTipoUnidadDto;
import org.Almacen.TopAlmacen.Mappers.PrecioPorTipoUnidadMapper;
import org.Almacen.TopAlmacen.Model.PrecioPorTipoUnidad;
import org.Almacen.TopAlmacen.Model.Producto;
import org.Almacen.TopAlmacen.Model.StockUnidades;
import org.Almacen.TopAlmacen.Model.TipoUnidad;
import org.Almacen.TopAlmacen.DAO.ITipoUnidadDao;

import java.util.List;
import java.util.stream.Collectors;

@Stateless
@LocalBean
public class PrecioPorTipoUnidadService {

    @Inject
    private IPrecioPorTipoUnidadDao iprecioPorTipoUnidadDao;
    @Inject
    private ITipoUnidadDao itipoUnidadDao;

    @Transactional
    public List<PrecioPorTipoUnidadDto> getAllPrecioPorTipoUnidad() {
        var preciosPorTipodeUnidades = iprecioPorTipoUnidadDao.getAll();
        return preciosPorTipodeUnidades.stream().map(p -> new PrecioPorTipoUnidadDto(p.getId(), p.getTipoUnidad(), p.getProducto(), p.getPrecioUnitario(), p.getUnidadesPorTipoUnidadDeProducto())).collect(Collectors.toList());
    }

    @Transactional
    public PrecioPorTipoUnidadDto getPrecioPorTipoUnidadById(int id) {
        var precioPorTipoUnidad = iprecioPorTipoUnidadDao.getById(id);
        return PrecioPorTipoUnidadMapper.toDto(precioPorTipoUnidad);
    }
    @Transactional
    public boolean verificarUnidad(Producto p, TipoUnidad tipoUnidad) {
        var existente = iprecioPorTipoUnidadDao.findIfExist(p, tipoUnidad);
        if (existente != null) {
            return true;
        }
        return false;
    }

    @Transactional
    public PrecioPorTipoUnidad CrearUnidadBasica(CreatePrecioPorTipoUnidadDto dto) {
        String abrevUnidadBasica = "UND";
        if (!verificarUnidad(dto.getProducto(), dto.getTipoUnidad())) {
            var tipoUnidad = itipoUnidadDao.findByAbrev(abrevUnidadBasica);
            var nuevaUnidad = new PrecioPorTipoUnidad();
            nuevaUnidad.setProducto(dto.getProducto());
            nuevaUnidad.setTipoUnidad(tipoUnidad);
            nuevaUnidad.setPrecioUnitario(0);
            nuevaUnidad.setUnidadesPorTipoUnidadDeProducto(1);

            var stockUnidades = new StockUnidades();
            stockUnidades.setCantidadStockUnidad(0);
            stockUnidades.setTipoUnidad(abrevUnidadBasica);
            stockUnidades.setPrecioPorTipoUnidad(nuevaUnidad);

            nuevaUnidad.setStockUnidades(stockUnidades);
            return iprecioPorTipoUnidadDao.create(nuevaUnidad);
        }

        return null;
    }

    @Transactional
    public PrecioPorTipoUnidad crearProductoConUnidadSuperior(CreatePrecioPorTipoUnidadDto dto) {
        if (!verificarUnidad(dto.getProducto(), dto.getTipoUnidad())) {
            var pptu = PrecioPorTipoUnidadMapper.toPrecioPorTipoUnidadFromCreate(dto);
            var stockUnidades = new StockUnidades();
            stockUnidades.setCantidadStockUnidad(dto.getUnidadesPorTipoUnidadPorProducto());
            stockUnidades.setTipoUnidad(pptu.getTipoUnidad().getAbrev());
            stockUnidades.setPrecioPorTipoUnidad(pptu);

            pptu.setStockUnidades(stockUnidades);

            return iprecioPorTipoUnidadDao.create(pptu);
        }
        return null;
    }

    @Transactional
    public PrecioPorTipoUnidad update(UpdatePrecioPorTipoUnidadDto dto, int id) {
        var pptu = PrecioPorTipoUnidadMapper.toPrecioPorTipoUnidadFromUpdate(dto);
        return iprecioPorTipoUnidadDao.update(dto, id);
    }

    @Transactional
    public PrecioPorTipoUnidad delete(int id) {
        return iprecioPorTipoUnidadDao.delete(id);
    }

    @Transactional
    public PrecioPorTipoUnidad findIfExists(Producto p, TipoUnidad t) {
        return iprecioPorTipoUnidadDao.findIfExist(p, t);
    }
}
