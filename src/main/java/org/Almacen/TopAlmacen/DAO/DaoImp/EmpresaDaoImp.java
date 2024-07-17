package org.Almacen.TopAlmacen.DAO.DaoImp;

import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.Almacen.TopAlmacen.DAO.IEmpresaDao;
import org.Almacen.TopAlmacen.DTO.Empresa.UpdateEmpresaDto;
import org.Almacen.TopAlmacen.Model.Categoria;
import org.Almacen.TopAlmacen.Model.Empresa;

import java.util.List;

@Named
public class EmpresaDaoImp implements IEmpresaDao {
    @PersistenceContext(name = "YourPU")
    private EntityManager _entityManager;

    @Override
    public List<Empresa> getAll() {
        return _entityManager.createQuery("SELECT e FROM Empresa e JOIN FETCH e.tipoEmpresa ORDER BY e.NroRUC ASC", Empresa.class).getResultList();
    }

    @Override
    public List<Empresa> getAllInactiveEstado() {
        return _entityManager.createQuery("SELECT e FROM Empresa e JOIN FETCH e.tipoEmpresa WHERE e.estado='INACTIVO' ORDER BY e.NroRUC ASC", Empresa.class).getResultList();
    }

    @Override
    public boolean isEmpresaAsociada(String empresaId) {
        Long count = _entityManager.createQuery("SELECT COUNT(p) FROM ProductoProveedorEntrada p WHERE p.empresa.NroRUC = :empresaId", Long.class).setParameter("empresaId", empresaId).getSingleResult();
        return count > 0;
    }

    @Override
    public void changeState(String empresaId, String estado) {
        var findObj = _entityManager.find(Empresa.class, empresaId);
        findObj.setEstado(estado);
        _entityManager.merge(findObj);
    }

    @Override
    public Empresa getById(String NroRuc) {
        return _entityManager.createQuery("SELECT d FROM Empresa d LEFT JOIN FETCH d.tipoEmpresa WHERE d.NroRUC = :NroRuc", Empresa.class).setParameter("NroRuc", NroRuc).getSingleResult();
    }

    @Override
    public Empresa create(Empresa c) {
        _entityManager.persist(c);
        return c;
    }

    @Override
    public Empresa update(UpdateEmpresaDto c, String NroRuc) {
        var empresa = _entityManager.find(Empresa.class, NroRuc);
        if (empresa != null) {
            empresa.setNombre(c.getNombre());
            empresa.setTipoEmpresa(c.getTipoEmpresa());
            empresa.setDireccion(c.getDireccion());
            return empresa;
        } else {
            return null;
        }
    }

    @Override
    public Empresa delete(String NroRuc) {
        var empresa = _entityManager.find(Empresa.class, NroRuc);
        if (empresa != null) {
            _entityManager.remove(empresa);
            return empresa;
        } else {
            return null;
        }
    }

    @Override
    public boolean exist(String NroRuc) {
        var query = _entityManager.createQuery("SELECT count(c) FROM Empresa c WHERE c.NroRUC = :NroRuc", Long.class);
        query.setParameter("NroRuc", NroRuc);
        Long count = query.getSingleResult();
        return count > 0;
    }
}
