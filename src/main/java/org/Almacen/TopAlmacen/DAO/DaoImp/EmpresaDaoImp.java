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
        return _entityManager.createQuery("SELECT e FROM Empresa e", Empresa.class).getResultList();
    }

    @Override
    public Empresa getById(int id) {
        return _entityManager.find(Empresa.class, id);
    }

    @Override
    public Empresa create(Empresa c) {
        return _entityManager.merge(c);
    }

    @Override
    public Empresa update(UpdateEmpresaDto c, int id) {
        var empresa = _entityManager.find(Empresa.class, id);
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
    public Empresa delete(int id) {
        var empresa = _entityManager.find(Empresa.class, id);
        if (empresa != null) {
            _entityManager.remove(empresa);
            return empresa;
        }else {
            return null;
        }
    }
}
