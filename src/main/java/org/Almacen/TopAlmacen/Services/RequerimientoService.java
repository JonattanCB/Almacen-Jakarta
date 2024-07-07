package org.Almacen.TopAlmacen.Services;


import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import org.Almacen.TopAlmacen.DAO.IItemsRequerimientoDao;
import org.Almacen.TopAlmacen.DAO.IRequerimientoDao;
import org.Almacen.TopAlmacen.DTO.ItemsRequerimiento.CreateItemsRequerimientoDto;
import org.Almacen.TopAlmacen.DTO.Requerimiento.CreateRequerimientoDto;
import org.Almacen.TopAlmacen.DTO.Requerimiento.UpdateRequerimientoDto;
import org.Almacen.TopAlmacen.Mappers.ItemsRequerimientoMapper;
import org.Almacen.TopAlmacen.Mappers.RequerimientoMapper;
import org.Almacen.TopAlmacen.Model.Requerimiento;

import java.util.List;

@Stateless
@LocalBean
public class RequerimientoService {

    @Inject
    private IRequerimientoDao iRequerimientoDao;
    @Inject
    private IItemsRequerimientoDao iItemsRequerimientoDao;

    public List<Requerimiento> getRequerimientos() {
        return iRequerimientoDao.getAll();
    }

    public Requerimiento getRequerimiento(int id) {
        return iRequerimientoDao.getById(id);
    }

    public Requerimiento create(CreateRequerimientoDto dto, List<CreateItemsRequerimientoDto> itemsDto) {
        var created = RequerimientoMapper.fromCreate(dto);
        iRequerimientoDao.create(created);
        for (CreateItemsRequerimientoDto item : itemsDto) {
            var newItem = ItemsRequerimientoMapper.fromCreate(item);
            iItemsRequerimientoDao.create(newItem);
        }
        return created;
    }

    public Requerimiento update(UpdateRequerimientoDto dto, int id) {
        return iRequerimientoDao.update(dto, id);
    }

    public Requerimiento delete(int id) {
        return iRequerimientoDao.delete(id);
    }
}
