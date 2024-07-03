package org.Almacen.TopAlmacen.Services;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.Almacen.TopAlmacen.DAO.IMovimientoStockDao;
import org.Almacen.TopAlmacen.Model.MovimientoStock;

import java.util.List;

@Stateless
@LocalBean
public class MovimientoStockService {

    @Inject
    private IMovimientoStockDao iMovimientoStockDao;

    @Transactional
    public List<MovimientoStock> getAllMovimientoStock() {
        return iMovimientoStockDao.getAll();
    }

    @Transactional
    public MovimientoStock getMovimientoStockById(int id) {
        return iMovimientoStockDao.getById(id);
    }
}
