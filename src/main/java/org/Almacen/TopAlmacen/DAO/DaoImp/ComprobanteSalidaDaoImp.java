package org.Almacen.TopAlmacen.DAO.DaoImp;

import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.Almacen.TopAlmacen.DAO.IComprobanteSalidaDao;
import org.Almacen.TopAlmacen.DTO.ComprobanteSalida.UpdateComprobanteSalidaDto;
import org.Almacen.TopAlmacen.Model.Categoria;
import org.Almacen.TopAlmacen.Model.ComprobanteSalida;

import java.util.List;

@Named
public class ComprobanteSalidaDaoImp implements IComprobanteSalidaDao {

    @PersistenceContext(name = "YourPU")
    private EntityManager _entityManager;

    @Override
    public List<ComprobanteSalida> getAll() {
        return _entityManager.createQuery("SELECT c FROM ComprobanteSalida c", ComprobanteSalida.class).getResultList();
    }

    @Override
    public ComprobanteSalida getById(int id) {
        var comprobanteSalida = _entityManager.find(ComprobanteSalida.class, id);
        _entityManager.detach(comprobanteSalida);
        return comprobanteSalida;
    }

    @Override
    public ComprobanteSalida create(ComprobanteSalida c) {
        var comprobanteSalida = _entityManager.merge(c);
        _entityManager.detach(comprobanteSalida);
        return  comprobanteSalida;

    }

    @Override
    public ComprobanteSalida update(UpdateComprobanteSalidaDto updateComprobanteSalidaDto, int id) {
        var existingComprobante = _entityManager.find(ComprobanteSalida.class, id);
        if (existingComprobante != null) {
            existingComprobante.setUnidadDependencia(updateComprobanteSalidaDto.getUnidadDependencia());
            existingComprobante.setParaUso(updateComprobanteSalidaDto.getParaUso());
            existingComprobante.setObservacion(updateComprobanteSalidaDto.getObservacion());
            _entityManager.merge(existingComprobante);
            return existingComprobante;
        } else {
            return null;
        }
    }

    @Override
    public ComprobanteSalida delete(int id) {
        var comprobanteSalida = _entityManager.find(ComprobanteSalida.class, id);
        if (comprobanteSalida != null) {
            _entityManager.remove(comprobanteSalida);
            return comprobanteSalida;
        } else {
            return null;
        }
    }
}
