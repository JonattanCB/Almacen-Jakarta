package org.Almacen.TopAlmacen.Services;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.Almacen.TopAlmacen.DAO.IHistorialPreciosDao;
import org.Almacen.TopAlmacen.Model.HistorialPrecios;

import java.util.List;

@Stateless
@LocalBean
public class HistorialPreciosService {

    @Inject
    private IHistorialPreciosDao iHistorialPreciosDao;

    @Transactional
    public List<HistorialPrecios> getAllHistorialPrecios() {
        return iHistorialPreciosDao.getAll();
    }

    @Transactional
    public HistorialPrecios getHistorialPrecioById(int id) {
        return iHistorialPreciosDao.getById(id);
    }
}
