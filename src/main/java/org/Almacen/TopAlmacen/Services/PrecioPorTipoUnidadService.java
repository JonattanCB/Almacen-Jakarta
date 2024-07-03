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
import org.Almacen.TopAlmacen.Mappers.ProductoMapper;
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
    public List<PrecioPorTipoUnidadDto> getAllPrecioPorTipoUnidad() { //LISTAR
        var preciosPorTipodeUnidades = iprecioPorTipoUnidadDao.getAll();
        return preciosPorTipodeUnidades.stream().map(PrecioPorTipoUnidadMapper::toDto).collect(Collectors.toList());
    }

    @Transactional
    public PrecioPorTipoUnidadDto getPrecioPorTipoUnidadById(int id) { //BUSCAR
        var precioPorTipoUnidad = iprecioPorTipoUnidadDao.getById(id);
        return PrecioPorTipoUnidadMapper.toDto(precioPorTipoUnidad);
    }

    @Transactional
    private boolean verificarUnidad(Producto producto, TipoUnidad tipoUnidad) {
        var existente = iprecioPorTipoUnidadDao.findIfExist(producto, tipoUnidad);
        return existente != null;
    }

    @Transactional
    private boolean existeUnidadBasica(Producto producto) {
        String abrevUnidadBasica = "UND";
        var tipoUnidadBasica = itipoUnidadDao.findByAbrev(abrevUnidadBasica);
        return verificarUnidad(producto, tipoUnidadBasica);
    }

    @Transactional
    public PrecioPorTipoUnidad CrearUnidadBasica(CreatePrecioPorTipoUnidadDto dto) {// CUANDO ES UNIDAD
        String abrevUnidadBasica = "UND";
        if (!verificarUnidad(dto.getProducto(), dto.getTipoUnidad())) {
            var tipoUnidad = itipoUnidadDao.findByAbrev(abrevUnidadBasica);
            var nuevaUnidad = new PrecioPorTipoUnidad();
            nuevaUnidad.setProducto(dto.getProducto());
            nuevaUnidad.setTipoUnidad(tipoUnidad);
            nuevaUnidad.setPrecioUnitario(dto.getPrecio());
            nuevaUnidad.setUnidadesPorTipoUnidadDeProducto(1);

            var stockUnidades = new StockUnidades();
            stockUnidades.setDescripcion(ProductoMapper.toConcatProduct(nuevaUnidad.getProducto()));
            stockUnidades.setCantidadStockUnidad(0);
            stockUnidades.setTipoUnidad(abrevUnidadBasica);

            istockUnidadesDao.create(stockUnidades);
            nuevaUnidad.setStockUnidades(stockUnidades);

            return iprecioPorTipoUnidadDao.create(nuevaUnidad);
        }

        return null;
    }

    @Transactional
    public PrecioPorTipoUnidad crearProductoConUnidadSuperior(CreatePrecioPorTipoUnidadDto dto) {//CUANDO ES PAQUETE
        if (existeUnidadBasica(dto.getProducto())) {
            var pptu = PrecioPorTipoUnidadMapper.toPrecioPorTipoUnidadFromCreate(dto);

            var stockUnidades = new StockUnidades();
            stockUnidades.setDescripcion(ProductoMapper.toConcatProduct(dto.getProducto()));
            stockUnidades.setCantidadStockUnidad(dto.getUnidadesPorTipoUnidadPorProducto());
            stockUnidades.setTipoUnidad(pptu.getTipoUnidad().getAbrev());

            istockUnidadesDao.create(stockUnidades);
            pptu.setStockUnidades(stockUnidades);

            return iprecioPorTipoUnidadDao.create(pptu);
        } else {
            System.out.println("No existe una unidad básica del tipo 'UND' para este producto.");
            return null;
        }
    }

    @Transactional
    public PrecioPorTipoUnidad update(UpdatePrecioPorTipoUnidadDto dto, int id) { //Actualizar
        var getItem = iprecioPorTipoUnidadDao.getById(id);
        if (getItem != null) {
            var pptu = PrecioPorTipoUnidadMapper.toPrecioPorTipoUnidadFromUpdate(dto);
            var pA = pptu.getPrecioUnitario();
            var pN = getItem.getPrecioUnitario();
            if (pA != pN) {
                var hp = new HistorialPrecios();
                hp.setPrecioPorTipoUnidad(getItem);
                hp.setPrecioRegistro(pN);
                ihistorialPreciosDao.create(hp);
            }
            getItem.setPrecioUnitario(dto.getPrecio());
            getItem.setUnidadesPorTipoUnidadDeProducto(dto.getUnidadesPorTipoUnidadPorProducto());
            getItem.setTipoUnidad(dto.getTipoUnidad());
            getItem.setProducto(dto.getProducto());
            return iprecioPorTipoUnidadDao.update(dto, id);
        }
        return null;
    }

    @Transactional
    public PrecioPorTipoUnidad delete(int id) {
        var pptu = iprecioPorTipoUnidadDao.getById(id);
        if (pptu != null) {
            if (pptu.getTipoUnidad().getAbrev().equals("UND")) {
                var stockUnidades = pptu.getStockUnidades();
                if (stockUnidades != null) {
                    istockUnidadesDao.delete(stockUnidades.getId());
                }
            }
            return iprecioPorTipoUnidadDao.delete(id);
        }
        return null;
    }
}
