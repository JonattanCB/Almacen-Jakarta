package org.Almacen.Siman.DAO.DaoImp;

import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.Almacen.Siman.DAO.ITipoEmpresaDao;
import org.Almacen.Siman.DTO.Empresa.UpdateEmpresaDto;
import org.Almacen.Siman.Model.TipoEmpresa;

import java.util.List;


@Named
public class TipoEmpresaImpl implements ITipoEmpresaDao {
    @PersistenceContext(name = "YourPU")
    private EntityManager _entityManager;

    @Override
    public List<TipoEmpresa> getAll() {
        return _entityManager.createQuery("SELECT c FROM TipoEmpresa c order by c.id asc ", TipoEmpresa.class).getResultList();
    }

    @Override
    public TipoEmpresa getById(int id) {
        var query = _entityManager.createQuery(
                "SELECT c FROM TipoEmpresa c  WHERE c.id = :id", TipoEmpresa.class
        );
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public void create(TipoEmpresa c) {
    }

    @Override
    public void update(UpdateEmpresaDto c, int id) {
    }

    @Override
    public void delete(int id) {
    }
}
