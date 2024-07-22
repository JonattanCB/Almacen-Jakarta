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
        if (!verificarUnidad(dto.getProducto(), dto.getTipoUnidad())) {
            var nuevaUnidad = PrecioPorTipoUnidadMapper.toPrecioPorTipoUnidadFromCreate(dto);
            nuevaUnidad.setUnidadesPorTipoUnidadDeProducto(1);
            var created = iprecioPorTipoUnidadDao.create(nuevaUnidad);

            var hp = new HistorialPrecios();
            hp.setPrecioPorTipoUnidad(created);
            hp.setPrecioRegistro(dto.getPrecio());
            ihistorialPreciosDao.create(hp);

            return created;
        }

        return null;
    }

    @Transactional
    public PrecioPorTipoUnidad crearProductoConUnidadSuperior(CreatePrecioPorTipoUnidadDto dto) {//CUANDO ES PAQUETE
        if (existeUnidadBasica(dto.getProducto())) {
            var pptu = PrecioPorTipoUnidadMapper.toPrecioPorTipoUnidadFromCreate(dto);

            var createdUnidad = iprecioPorTipoUnidadDao.create(pptu);
            var historialPrecios = new HistorialPrecios();
            historialPrecios.setPrecioPorTipoUnidad(createdUnidad);
            historialPrecios.setPrecioRegistro(dto.getPrecio());
            ihistorialPreciosDao.create(historialPrecios);
            return createdUnidad;
        } else {
            System.out.println("No existe una unidad básica del tipo 'UND' para este producto.");
            return null;
        }
    }

    @Transactional
    public PrecioPorTipoUnidad eliminarUnidadBasica(PrecioPorTipoUnidadDto dto) {
        var unidades = getAllTipoUnidadByIdProducto(dto.getProducto().getId());

        if (unidades.size() != 1) {
            return null;
        }
        var pptu = PrecioPorTipoUnidadMapper.toEntity(dto);
        iprecioPorTipoUnidadDao.delete(pptu.getId());
        return pptu;

    }

    @Transactional
    public PrecioPorTipoUnidad eliminarUnidadSuperior(PrecioPorTipoUnidadDto dto) {
        if (dto.getTipoUnidad().getAbrev().equals("UND")) {
            return eliminarUnidadBasica(dto);
        }
        iprecioPorTipoUnidadDao.delete(dto.getId());
        return PrecioPorTipoUnidadMapper.toEntity(dto);
    }

    @Transactional
    public PrecioPorTipoUnidad update(UpdatePrecioPorTipoUnidadDto dto, int id, Usuario usuario) { //Actualizar
        var getItem = iprecioPorTipoUnidadDao.getById(id);
        if (getItem != null) {
            var pptu = PrecioPorTipoUnidadMapper.toPrecioPorTipoUnidadFromUpdate(dto);
            var pN = pptu.getPrecioUnitario();
            var pA = getItem.getPrecioUnitario();
            if (pN != pA) {
                var hp = new HistorialPrecios();
                hp.setPrecioPorTipoUnidad(getItem);
                hp.setPrecioRegistro(pN);
                hp.setResponsable(usuario);
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
    public PrecioPorTipoUnidadDto getByIdProductoIdTipoUnidad(int idProducto, int idTipoUnidad) {
        return PrecioPorTipoUnidadMapper.toDto(iprecioPorTipoUnidadDao.getByIdProductoIdTipoUnidad(idProducto, idTipoUnidad));
    }

    @Transactional
    public PrecioPorTipoUnidadDto getByIdProducto(int idProducto) {
        var precioTU = iprecioPorTipoUnidadDao.getByIdProducto(idProducto);
        if (precioTU == null) {
            return null;
        } else {
            return PrecioPorTipoUnidadMapper.toDto(precioTU);
        }
    }

    @Transactional
    public List<PrecioPorTipoUnidad> getAllTipoUnidadByIdProducto(int idProducto) {
        return iprecioPorTipoUnidadDao.getAllTipoUnidadbyProducto(idProducto);
    }


}
