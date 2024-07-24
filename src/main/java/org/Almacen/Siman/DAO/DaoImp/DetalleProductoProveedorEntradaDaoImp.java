package org.Almacen.Siman.DAO.DaoImp;

import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.Almacen.Siman.DAO.IDetalleProductoProveedorEntradaDao;
import org.Almacen.Siman.DTO.DetalleProductoProveedorEntrada.UpdateDetalleProductoProveedorEntradaDto;
import org.Almacen.Siman.Model.DetalleProductoProveedorEntrada;

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
    public List<DetalleProductoProveedorEntrada> getAllByProveedorEntrada(String id) {
        var query = _entityManager.createQuery(
                "SELECT d FROM DetalleProductoProveedorEntrada d LEFT JOIN FETCH d.OC_id LEFT JOIN FETCH d.tipoUnidad LEFT JOIN FETCH  d.producto  WHERE d.OC_id.OC = :id",
                DetalleProductoProveedorEntrada.class);
        query.setParameter("id", id);

        return query.getResultList();
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
            findingObject.setProducto(dto.getProducto());
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
