package org.Almacen.TopAlmacen.Services;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.Almacen.TopAlmacen.DAO.IDetalleProductoProveedorEntradaDao;
import org.Almacen.TopAlmacen.DTO.DetalleProductoProveedorEntrada.CreateDetalleProductoProveedorEntradaDto;
import org.Almacen.TopAlmacen.DTO.DetalleProductoProveedorEntrada.DetalleProductoProveedorEntradaDto;
import org.Almacen.TopAlmacen.Mappers.DetalleProductoProveedorEntradaMapper;
import org.Almacen.TopAlmacen.Model.DetalleProductoProveedorEntrada;

import java.util.List;
import java.util.stream.Collectors;

@Stateless
@LocalBean
public class DetalleProductoProveedorEntradaService {

    @Inject
    private IDetalleProductoProveedorEntradaDao iDetalleProductoProveedorEntradaDao;

    @Transactional
    public List<DetalleProductoProveedorEntradaDto> getAll() {
        var detalles = iDetalleProductoProveedorEntradaDao.getAll();
        return detalles.stream().map(DetalleProductoProveedorEntradaMapper::toDto).collect(Collectors.toList());
    }

    @Transactional
    public DetalleProductoProveedorEntradaDto getById(int id) {
        var detalle = iDetalleProductoProveedorEntradaDao.getById(id);
        return DetalleProductoProveedorEntradaMapper.toDto(detalle);
    }

    @Transactional
    public DetalleProductoProveedorEntrada add(CreateDetalleProductoProveedorEntradaDto dto) {
        var detalleAdd = DetalleProductoProveedorEntradaMapper.fromCreate(dto);
        return iDetalleProductoProveedorEntradaDao.create(detalleAdd);
    }
}
