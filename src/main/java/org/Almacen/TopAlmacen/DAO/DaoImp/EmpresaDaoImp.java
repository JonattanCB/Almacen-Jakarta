package org.Almacen.TopAlmacen.DAO.DaoImp;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.Almacen.TopAlmacen.DAO.IEmpresaDao;
import org.Almacen.TopAlmacen.DTO.Empresa.UpdateEmpresaDto;
import org.Almacen.TopAlmacen.Model.Empresa;

import java.util.List;

public class EmpresaDaoImp implements IEmpresaDao {
    @PersistenceContext(name = "YourPU")
    private EntityManager _entityManager;

    @Override
    public List<Empresa> getAll() {
        return _entityManager.createQuery("SELECT e FROM Empresa e order by  e.NroRUC asc ", Empresa.class).getResultList();
    }

    @Override
    public Empresa getById(String NroRuc) {
        return _entityManager.find(Empresa.class, NroRuc);
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
}
