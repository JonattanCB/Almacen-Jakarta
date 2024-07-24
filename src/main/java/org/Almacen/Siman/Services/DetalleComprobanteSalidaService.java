package org.Almacen.Siman.Services;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.Almacen.Siman.DAO.IDetalleComprobanteSalidaDao;
import org.Almacen.Siman.DTO.DetalleComprobanteSalida.CreateDetalleComprobanteSalidaDto;
import org.Almacen.Siman.DTO.DetalleComprobanteSalida.DetalleComprobanteSalidaDto;
import org.Almacen.Siman.Mappers.DetalleComprobanteSalidaMapper;
import org.Almacen.Siman.Model.DetalleComprobanteSalida;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
@LocalBean
public class DetalleComprobanteSalidaService implements Serializable {

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
