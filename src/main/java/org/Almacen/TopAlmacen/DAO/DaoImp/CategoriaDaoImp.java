package org.Almacen.TopAlmacen.DAO.DaoImp;

import jakarta.ejb.Stateless;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.Almacen.TopAlmacen.DAO.ICategoriaDao;
import org.Almacen.TopAlmacen.DTO.Categoria.UpdateCategoriaDto;
import org.Almacen.TopAlmacen.Model.Categoria;

import java.util.List;

@Named
public class CategoriaDaoImp implements ICategoriaDao {
    @PersistenceContext(name = "YourPU")
    private EntityManager _entityManager;

    @Override
    public List<Categoria> getAll() {
        System.out.println("Ingresando a la capa DAO getAll");
        return _entityManager.createQuery("SELECT c FROM Categoria c", Categoria.class).getResultList();
    }

    @Override
    public List<Categoria> getAllByEstadoActivo() {
        return _entityManager.createQuery("SELECT c FROM Categoria c WHERE c.estado='Activo'", Categoria.class).getResultList();
    }

    @Override
    public Categoria getById(int id) {
        var categoria = _entityManager.find(Categoria.class, id);
        _entityManager.detach(categoria);
        return categoria;
    }

    @Override
    public Categoria create(Categoria c) {
        System.out.println("Ingresando a la capa Dao create");
        _entityManager.persist(c);
        return c;
    }

    @Override
    public Categoria update(UpdateCategoriaDto updateCategoriaDto, int id) {
        var existingCategoria = _entityManager.find(Categoria.class, id);
        if (existingCategoria != null) {
            existingCategoria.setNombre(updateCategoriaDto.getNombre());
            existingCategoria.setDescripcion(updateCategoriaDto.getDescripcion());
            existingCategoria.setEstado(updateCategoriaDto.getEstado());
            _entityManager.merge(existingCategoria);
            return existingCategoria;
        } else {
            return null;
        }
    }

    @Override
    public Categoria delete(int id) {
        var categoria = _entityManager.find(Categoria.class, id);
        if (categoria != null) {
            _entityManager.remove(categoria);
            return categoria;
        } else {
            return null;
        }
    }
}
