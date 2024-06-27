package org.Almacen.TopAlmacen.DAO.DaoImp;

import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.Almacen.TopAlmacen.DAO.IDetalleProductoProveedorEntradaDao;
import org.Almacen.TopAlmacen.DTO.DetalleProductoProveedorEntrada.UpdateDetalleProductoProveedorEntradaDto;
import org.Almacen.TopAlmacen.Model.DetalleProductoProveedorEntrada;

import java.util.List;

@Named
public class DetalleProductoProveedorEntradaDaoImp implements IDetalleProductoProveedorEntradaDao {
    @PersistenceContext(name = "YourPU")
    private EntityManager _entityManager;

    @Override
    public List<DetalleProductoProveedorEntrada> getAll() {
        return _entityManager.createQuery("SELECT d FROM DetalleProductoProveedorEntrada d", DetalleProductoProveedorEntrada.class).getResultList();
    }

    @Override
    public List<DetalleProductoProveedorEntrada> getAllByProveedorEntrada(int id) {
        return _entityManager.createQuery("SELECT d FROM DetalleProductoProveedorEntrada d WHERE d.OC_id = 'id'", DetalleProductoProveedorEntrada.class).getResultList();
    }

    @Override
    public DetalleProductoProveedorEntrada getById(int id) {
        var existingObject = _entityManager.find(DetalleProductoProveedorEntrada.class, id);
        if (existingObject != null) {
            return existingObject;
        } else {
            return null;
        }
    }

    @Override
    public DetalleProductoProveedorEntrada create(DetalleProductoProveedorEntrada c) {
        _entityManager.persist(c);
        return c;
    }

    @Override
    public DetalleProductoProveedorEntrada update(UpdateDetalleProductoProveedorEntradaDto dto, int id) {
        var findingObject = _entityManager.find(DetalleProductoProveedorEntrada.class, id);
        if (findingObject != null) {
            findingObject.setCantidad(dto.getCantidad());
            findingObject.setTipoUnidad(dto.getTipoUnidad());
            findingObject.setDescripcion(dto.getDescripcion());
            findingObject.setDescuento(dto.getDescuento());
            findingObject.setPrecioUnitario(dto.getPrecioUnitario());
            findingObject.setPrecioTotal(dto.getPrecioUnitario() * dto.getCantidad());
            return findingObject;
        } else {
            return null;
        }
    }

    @Override
    public DetalleProductoProveedorEntrada delete(int id) {
        return _entityManager.find(DetalleProductoProveedorEntrada.class, id);
    }
}
