package org.Almacen.TopAlmacen.DAO.DaoImp;

import jakarta.ejb.Stateless;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.Almacen.TopAlmacen.DAO.ICategoriaDao;
import org.Almacen.TopAlmacen.DTO.Categoria.UpdateCategoriaDto;
import org.Almacen.TopAlmacen.Model.Categoria;

import java.util.ArrayList;
import java.util.List;

@Named
public class CategoriaDaoImp implements ICategoriaDao {
    @PersistenceContext(name = "YourPU")
    private EntityManager _entityManager;

    @Override
    public List<Categoria> getAll() {
        return  _entityManager.createQuery("SELECT c FROM Categoria c", Categoria.class).getResultList();
    }

    @Override
    public Categoria getById(int id) {
        var categoria = _entityManager.find(Categoria.class, id);
        _entityManager.detach(categoria);
        return categoria;
    }

    @Override
    public void create(Categoria c) {
        _entityManager.persist(c);
    }

    @Override
    public void update(UpdateCategoriaDto updateCategoriaDto, int id) {
        var existingCategoria = _entityManager.find(Categoria.class, id);
        if (existingCategoria != null) {
            existingCategoria.setNombre(updateCategoriaDto.getNombre());
            existingCategoria.setDescripcion(updateCategoriaDto.getDescripcion());
            existingCategoria.setEstado(updateCategoriaDto.getEstado());
            _entityManager.merge(existingCategoria);
        }
    }

    @Override
    public void delete(int id) {
        var categoria = _entityManager.find(Categoria.class, id);
        if (categoria != null) {
            _entityManager.remove(categoria);
        }
    }
}
