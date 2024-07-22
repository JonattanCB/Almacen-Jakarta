package org.Almacen.TopAlmacen.DAO.DaoImp;

import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.Almacen.TopAlmacen.DAO.IComprobanteSalidaDao;
import org.Almacen.TopAlmacen.DTO.ComprobanteSalida.UpdateComprobanteSalidaDto;
import org.Almacen.TopAlmacen.Model.Categoria;
import org.Almacen.TopAlmacen.Model.ComprobanteSalida;
import org.Almacen.TopAlmacen.Model.ProductoProveedorEntrada;

import java.util.List;

@Named
public class ComprobanteSalidaDaoImp implements IComprobanteSalidaDao {

    @PersistenceContext(name = "YourPU")
    private EntityManager _entityManager;

    @Override
    public List<ComprobanteSalida> getAll() {
        return _entityManager.createQuery("SELECT c FROM ComprobanteSalida c  LEFT JOIN FETCH c.usuario LEFT JOIN FETCH  c.dependencia LEFT JOIN FETCH c.usuario.unidadDependencia", ComprobanteSalida.class).getResultList();
    }

    @Override
    public ComprobanteSalida getById(String id) {
        return _entityManager.createQuery("SELECT c FROM ComprobanteSalida c LEFT JOIN FETCH c.dependencia  LEFT JOIN FETCH c.usuario  WHERE c.id = :id",
                ComprobanteSalida.class).setParameter("id", id).getSingleResult();
    }

    @Override
    public ComprobanteSalida create(ComprobanteSalida c) {
        _entityManager.persist(c);
        System.out.println("aca2");
        return c;
    }

    @Override
    public ComprobanteSalida update(UpdateComprobanteSalidaDto updateComprobanteSalidaDto, String id) {
        var existingComprobante = _entityManager.find(ComprobanteSalida.class, id);
        if (existingComprobante != null) {
            existingComprobante.setDependencia(updateComprobanteSalidaDto.getDependencia());
            existingComprobante.setParaUso(updateComprobanteSalidaDto.getParaUso());
            existingComprobante.setObservacion(updateComprobanteSalidaDto.getObservacion());
            _entityManager.merge(existingComprobante);
            return existingComprobante;
        } else {
            return null;
        }
    }

    @Override
    public ComprobanteSalida delete(String id) {
        var comprobanteSalida = _entityManager.find(ComprobanteSalida.class, id);
        if (comprobanteSalida != null) {
            _entityManager.remove(comprobanteSalida);
            return comprobanteSalida;
        } else {
            return null;
        }
    }

    @Override
    public void setEstado(String estado, String id) {
        var findObj= getById(id);
        if (findObj != null) {
            findObj.setEstado(estado);
            _entityManager.merge(findObj);
        }
    }
}
