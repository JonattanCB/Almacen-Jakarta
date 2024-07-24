package org.Almacen.Siman.Services;


import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.Almacen.Siman.DAO.IItemsRequerimientoDao;
import org.Almacen.Siman.DAO.IRequerimientoDao;
import org.Almacen.Siman.DTO.ItemsRequerimiento.CreateItemsRequerimientoDto;
import org.Almacen.Siman.DTO.Requerimiento.CreateRequerimientoDto;
import org.Almacen.Siman.DTO.Requerimiento.UpdateRequerimientoDto;
import org.Almacen.Siman.Mappers.ItemsRequerimientoMapper;
import org.Almacen.Siman.Mappers.RequerimientoMapper;
import org.Almacen.Siman.Model.ItemsRequerimiento;
import org.Almacen.Siman.Model.Requerimiento;

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
    public List<Requerimiento> getRequerimientosStatusPendiente(int idDependencia) {
        return iRequerimientoDao.getAllbyStatusPendiente(idDependencia);
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
    public List<Requerimiento> getAllByUnidadUser(int unidad, int user){
        return  iRequerimientoDao.getAllByIdDependenciaUser(unidad, user);
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

    @Transactional
    public int setCantidadRequermientoStatus(int user, int unidad, String status){
        return iRequerimientoDao.cantidadRequerimientosUserUnidadStatus(unidad,user,status);
    }

    @Transactional
    public int setCantidadRequerimientoUser(int user, int unidad){
        return iRequerimientoDao.cantidadRequerimientosUserUnidad(unidad,user);
    }

    @Transactional
    public int setCantidadRequermientoDependenciaStatus(int dependencia, String status){
        return iRequerimientoDao.cantidadRequerimientosdependenciaStatus(dependencia,status);
    }

    @Transactional
    public int setCantidadRequerimientoDependencia(int dependencia){
        return iRequerimientoDao.cantidadRequerimientosDependencia(dependencia);
    }

}
