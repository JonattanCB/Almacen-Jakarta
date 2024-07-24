package org.Almacen.Siman.Services;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.Almacen.Siman.DAO.IDetalleProductoProveedorEntradaDao;
import org.Almacen.Siman.DAO.IProductoProveedorEntradaDao;
import org.Almacen.Siman.DTO.DetalleProductoProveedorEntrada.CreateDetalleProductoProveedorEntradaDto;
import org.Almacen.Siman.DTO.DetalleProductoProveedorEntrada.DetalleProductoProveedorEntradaDto;
import org.Almacen.Siman.DTO.DetalleProductoProveedorEntrada.UpdateDetalleProductoProveedorEntradaDto;
import org.Almacen.Siman.Mappers.DetalleProductoProveedorEntradaMapper;
import org.Almacen.Siman.Model.DetalleProductoProveedorEntrada;

import java.util.List;
import java.util.stream.Collectors;

@Stateless
@LocalBean
public class DetalleProductoProveedorEntradaService {

    @Inject
    private IDetalleProductoProveedorEntradaDao iDetalleProductoProveedorEntradaDao;
    @Inject
    private IProductoProveedorEntradaDao iProductoProveedorEntradaDao;

    @Transactional
    public List<DetalleProductoProveedorEntradaDto> getAll() {
        var detalles = iDetalleProductoProveedorEntradaDao.getAll();
        return detalles.stream().map(DetalleProductoProveedorEntradaMapper::toDto).collect(Collectors.toList());
    }

    @Transactional
    public List<DetalleProductoProveedorEntrada> getAllByProveedorEntradaId(String id) {
        return iDetalleProductoProveedorEntradaDao.getAllByProveedorEntrada(id);

    }

    @Transactional
    public DetalleProductoProveedorEntradaDto getById(int id) {
        var detalle = iDetalleProductoProveedorEntradaDao.getById(id);
        return DetalleProductoProveedorEntradaMapper.toDto(detalle);
    }

    @Transactional
    public DetalleProductoProveedorEntrada create(CreateDetalleProductoProveedorEntradaDto dto) {
        var detalleAdd = DetalleProductoProveedorEntradaMapper.fromCreate(dto);
        return iDetalleProductoProveedorEntradaDao.create(detalleAdd);
    }

    @Transactional
    public DetalleProductoProveedorEntrada update(UpdateDetalleProductoProveedorEntradaDto dto, int id) {
        var detalle = iDetalleProductoProveedorEntradaDao.getById(id);

        double precioAnterior = detalle.getPrecioTotal();
        double nuevoPrecio = dto.getPrecioUnitario() * dto.getCantidad();
        double diferenciaPrecio = nuevoPrecio - precioAnterior;

        detalle.setPrecioTotal(nuevoPrecio);
        iDetalleProductoProveedorEntradaDao.update(dto, id);

        var entrada = detalle.getOC_id();
        entrada.setPrecioFinal(entrada.getPrecioFinal() + diferenciaPrecio);
        iProductoProveedorEntradaDao.updatePrice(nuevoPrecio, entrada.getOC());

        return detalle;
    }

    @Transactional
    public void delete(int id) {

        iDetalleProductoProveedorEntradaDao.delete(id);
    }

}
