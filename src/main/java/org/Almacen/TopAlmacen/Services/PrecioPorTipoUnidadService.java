package org.Almacen.TopAlmacen.Services;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.Almacen.TopAlmacen.DAO.IHistorialPreciosDao;
import org.Almacen.TopAlmacen.DAO.IPrecioPorTipoUnidadDao;
import org.Almacen.TopAlmacen.DAO.IStockUnidadesDao;
import org.Almacen.TopAlmacen.DTO.PrecioPorTipoUnidad.CreatePrecioPorTipoUnidadDto;
import org.Almacen.TopAlmacen.DTO.PrecioPorTipoUnidad.PrecioPorTipoUnidadDto;
import org.Almacen.TopAlmacen.DTO.PrecioPorTipoUnidad.UpdatePrecioPorTipoUnidadDto;
import org.Almacen.TopAlmacen.Mappers.PrecioPorTipoUnidadMapper;
import org.Almacen.TopAlmacen.Model.*;
import org.Almacen.TopAlmacen.DAO.ITipoUnidadDao;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
@LocalBean
public class PrecioPorTipoUnidadService implements Serializable {

    @Inject
    private IPrecioPorTipoUnidadDao iprecioPorTipoUnidadDao;
    @Inject
    private ITipoUnidadDao itipoUnidadDao;
    @Inject
    private IHistorialPreciosDao ihistorialPreciosDao;
    @Inject
    private IStockUnidadesDao istockUnidadesDao;

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

    public boolean existeUnidadBasica(Producto producto) {
        String abrevUnidadBasica = "UND";
        var tipoUnidad = itipoUnidadDao.findByAbrev(abrevUnidadBasica);
        return iprecioPorTipoUnidadDao.findIfExist(producto, tipoUnidad) != null;
    }

    @Transactional
    public PrecioPorTipoUnidad CrearUnidadBasica(CreatePrecioPorTipoUnidadDto dto) {
        String abrevUnidadBasica = "UND";
        if (!verificarUnidad(dto.getProducto(), dto.getTipoUnidad())) {
            var tipoUnidad = itipoUnidadDao.findByAbrev(abrevUnidadBasica);
            var nuevaUnidad = new PrecioPorTipoUnidad();
            nuevaUnidad.setProducto(dto.getProducto());
            nuevaUnidad.setTipoUnidad(tipoUnidad);
            nuevaUnidad.setPrecioUnitario(dto.getPrecio());
            nuevaUnidad.setUnidadesPorTipoUnidadDeProducto(1);

            var stockUnidades = new StockUnidades();
            stockUnidades.setCantidadStockUnidad(0);
            stockUnidades.setTipoUnidad(abrevUnidadBasica);

            return iprecioPorTipoUnidadDao.create(nuevaUnidad);
        }

        return null;
    }

    @Transactional
    public PrecioPorTipoUnidad crearProductoConUnidadSuperior(CreatePrecioPorTipoUnidadDto dto) {
        if (existeUnidadBasica(dto.getProducto())) {
            var pptu = PrecioPorTipoUnidadMapper.toPrecioPorTipoUnidadFromCreate(dto);

            var stockUnidades = new StockUnidades();
            stockUnidades.setCantidadStockUnidad(dto.getUnidadesPorTipoUnidadPorProducto());
            stockUnidades.setTipoUnidad(pptu.getTipoUnidad().getAbrev());
            stockUnidades.getPreciosPorTipoUnidad().add(pptu);

            istockUnidadesDao.create(stockUnidades);
            return iprecioPorTipoUnidadDao.create(pptu);
        } else {
            System.out.println("No existe una unidad básica del tipo 'UND' para este producto.");
            return null;
        }
    }

    @Transactional
    public PrecioPorTipoUnidad update(UpdatePrecioPorTipoUnidadDto dto, int id) {
        var getItem = iprecioPorTipoUnidadDao.getById(id);
        if (getItem != null) {
            var pptu = PrecioPorTipoUnidadMapper.toPrecioPorTipoUnidadFromUpdate(dto);
            var pA = pptu.getPrecioUnitario();
            var pN = getItem.getPrecioUnitario();
            if (pA != pN) {
                var hp = new HistorialPrecios();
                hp.setPrecioPorTipoUnidad(pptu);
                hp.setPrecioRegistro(pN);
                ihistorialPreciosDao.create(hp);
            }
            return iprecioPorTipoUnidadDao.update(dto, id);
        }
        return null;
    }

    @Transactional
    public PrecioPorTipoUnidad delete(int id) {
        return iprecioPorTipoUnidadDao.delete(id);
    }
}
