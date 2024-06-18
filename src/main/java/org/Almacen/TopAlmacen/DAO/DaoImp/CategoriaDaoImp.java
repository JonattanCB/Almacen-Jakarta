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
        System.out.println("Ingresando a la consulta de categorías");
        List<Categoria> categories = null;
        try {
            TypedQuery<Categoria> query = _entityManager.createQuery("SELECT c FROM Categoria c", Categoria.class);
            categories = query.getResultList();
            for (Categoria c : categories) {
                _entityManager.detach(c);
                System.out.println(c.getNombre());
            }
        } catch (Exception e) {
            System.out.println("error en la consulta: "+e.getMessage());
        }
        return categories;
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
