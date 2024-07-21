package org.Almacen.TopAlmacen.Services;


import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.Almacen.TopAlmacen.DAO.IItemsRequerimientoDao;
import org.Almacen.TopAlmacen.DAO.IRequerimientoDao;
import org.Almacen.TopAlmacen.DTO.ItemsRequerimiento.CreateItemsRequerimientoDto;
import org.Almacen.TopAlmacen.DTO.Requerimiento.CreateRequerimientoDto;
import org.Almacen.TopAlmacen.DTO.Requerimiento.RequerimientoDto;
import org.Almacen.TopAlmacen.DTO.Requerimiento.UpdateRequerimientoDto;
import org.Almacen.TopAlmacen.Mappers.ItemsRequerimientoMapper;
import org.Almacen.TopAlmacen.Mappers.RequerimientoMapper;
import org.Almacen.TopAlmacen.Model.ItemsRequerimiento;
import org.Almacen.TopAlmacen.Model.Requerimiento;

import java.io.Serializable;
import java.util.List;

@Stateless
@LocalBean
public class RequerimientoService implements Serializable {

    @Inject
    private IRequerimientoDao iRequerimientoDao;

    @Inject
    private IItemsRequerimientoDao iItemsRequerimientoDao;

    @Transactional
    public List<Requerimiento> getRequerimientos(int idDependencia) {
        return iRequerimientoDao.getAll(idDependencia);
    }

    @Transactional
    public List<Requerimiento> getAllFinalized() {
        return iRequerimientoDao.getAllFinalized();
    }

    @Transactional
    public List<Requerimiento> getRequerimientosbyDependencia() {
        return iRequerimientoDao.getRequerimientoByDependencia();
    }

    @Transactional
    public Requerimiento getRequerimiento(String id) {
        var req = iRequerimientoDao.getById(id);
        return req;
    }

    @Transactional
    public List<ItemsRequerimiento> getItemsByRequerimientoId(String requerimientoId) {
        return iItemsRequerimientoDao.getItemsByRequerimientoId(requerimientoId);
    }

    @Transactional
    public Requerimiento create(CreateRequerimientoDto dto, List<CreateItemsRequerimientoDto> itemsDto) {
        var created = RequerimientoMapper.fromCreate(dto);
        iRequerimientoDao.create(created);
        for (CreateItemsRequerimientoDto item : itemsDto) {
            var newItem = ItemsRequerimientoMapper.fromCreate(item);
            newItem.setRequerimiento(created);
            iItemsRequerimientoDao.create(newItem);
        }
        return created;
    }

    @Transactional
    public Requerimiento update(UpdateRequerimientoDto dto, String id) {
        return iRequerimientoDao.update(dto, id);
    }

    @Transactional
    public Requerimiento delete(String id) {
        iItemsRequerimientoDao.deleteRequerimientoAll(id);
        return iRequerimientoDao.delete(id);
    }

    @Transactional
    public List<Requerimiento> getAllAprobed() {
        return iRequerimientoDao.getAllAprobed();
    }

    @Transactional
    public void setEstadoAprobado(String id, String observacion) {
        iRequerimientoDao.setEstado(id, "APROBADO", observacion);
    }

    @Transactional
    public void setEstadoDesaprobado(String id, String observacion) {
        iRequerimientoDao.setEstado(id, "DESAPROBADO", observacion);
    }

    @Transactional
    public void setEstadoFinalizado(String id, String observacion) {
        iRequerimientoDao.setEstado(id, "FINALIZADO", observacion);
    }
}
