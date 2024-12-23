package org.Almacen.Siman.DAO.DaoImp;

import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.Almacen.Siman.DAO.IComprobanteSalidaDao;
import org.Almacen.Siman.DTO.ComprobanteSalida.UpdateComprobanteSalidaDto;
import org.Almacen.Siman.Mappers.UsuarioMapper;
import org.Almacen.Siman.Model.ComprobanteSalida;
import org.Almacen.Siman.Model.Usuario;

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
    public ComprobanteSalida setAprobado(ComprobanteSalida cs, Usuario usuario){
        cs.setAprobadoPor(UsuarioMapper.toConcatuser(usuario));
        _entityManager.merge(cs);
        return cs;
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

    @Override
    public int cantStatus(String status) {
        Long count = _entityManager.createQuery(
                        "SELECT COUNT(c) FROM ComprobanteSalida c " +
                                "LEFT JOIN c.usuario " +
                                "LEFT JOIN c.dependencia " +
                                "LEFT JOIN c.usuario.unidadDependencia where c.estado = :status", Long.class)
                .setParameter("status",status)
                .getSingleResult();
        return count.intValue();
    }
}
