package org.Almacen.TopAlmacen.Services;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.Almacen.TopAlmacen.DAO.IDetalleComprobanteSalidaDao;
import org.Almacen.TopAlmacen.DTO.DetalleComprobanteSalida.CreateDetalleComprobanteSalidaDto;
import org.Almacen.TopAlmacen.DTO.DetalleComprobanteSalida.DetalleComprobanteSalidaDto;
import org.Almacen.TopAlmacen.Mappers.DetalleComprobanteSalidaMapper;
import org.Almacen.TopAlmacen.Model.DetalleComprobanteSalida;

import java.util.List;
import java.util.stream.Collectors;

@Stateless
@LocalBean
public class DetalleComprobanteSalidaService {

    @Inject
    private IDetalleComprobanteSalidaDao iDetalleComprobanteSalidaDao;

    @Transactional
    public List<DetalleComprobanteSalidaDto> getAll() {
        var listaDetalle = iDetalleComprobanteSalidaDao.getAll();
        return listaDetalle.stream().map(DetalleComprobanteSalidaMapper::toDto).collect(Collectors.toList());
    }

    @Transactional
    public DetalleComprobanteSalidaDto getById(int id) {
        var detalle = iDetalleComprobanteSalidaDao.getById(id);
        return DetalleComprobanteSalidaMapper.toDto(detalle);
    }

    @Transactional
    public DetalleComprobanteSalida create(CreateDetalleComprobanteSalidaDto dto) {
        var detalle = DetalleComprobanteSalidaMapper.fromCreate(dto);
        return iDetalleComprobanteSalidaDao.create(detalle);
    }
}
