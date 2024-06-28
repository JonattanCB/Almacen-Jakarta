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
    public PrecioPorTipoUnidad verificarOCrearUnidadBasica(Producto producto) {
        String abrevUnidadBasica = "UND"; // Abreviatura para unidad
        PrecioPorTipoUnidad existente = iprecioPorTipoUnidadDao.findIfExist(producto, abrevUnidadBasica);

        if (existente == null) {
            TipoUnidad tipoUnidadBasica = itipoUnidadDao.findOrCreateTipoUnidad(abrevUnidadBasica);
            PrecioPorTipoUnidad nuevaUnidad = new PrecioPorTipoUnidad();
            nuevaUnidad.setProducto(producto);
            nuevaUnidad.setTipoUnidad(tipoUnidadBasica);
            nuevaUnidad.setPrecioUnitario(0); // O establece el precio adecuado
            nuevaUnidad.setUnidadesPorTipoUnidadDeProducto(1);

            StockUnidades stockUnidades = new StockUnidades();
            stockUnidades.setCantidadStockUnidad(0); // Inicialmente sin stock
            stockUnidades.setTipoUnidad(abrevUnidadBasica);
            stockUnidades.setPrecioPorTipoUnidad(nuevaUnidad);

            nuevaUnidad.setStockUnidades(stockUnidades);
            return iprecioPorTipoUnidadDao.create(nuevaUnidad);
        }
        return existente;
    }

    @Transactional
    public PrecioPorTipoUnidad crearProductoConUnidadSuperior(CreatePrecioPorTipoUnidadDto dto) {
        Producto producto = dto.getProducto();
        verificarOCrearUnidadBasica(producto);

        PrecioPorTipoUnidad pptu = PrecioPorTipoUnidadMapper.toPrecioPorTipoUnidadFromCreate(dto);
        StockUnidades stockUnidades = new StockUnidades();
        stockUnidades.setCantidadStockUnidad(dto.getUnidadesPorTipoUnidadPorProducto());
        stockUnidades.setTipoUnidad(pptu.getTipoUnidad().getAbrev());
        stockUnidades.setPrecioPorTipoUnidad(pptu);

        pptu.setStockUnidades(stockUnidades);

        return iprecioPorTipoUnidadDao.create(pptu);
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
    public PrecioPorTipoUnidad findIfExists(Producto p, String t) {
        return iprecioPorTipoUnidadDao.findIfExist(p, t);
    }
}
