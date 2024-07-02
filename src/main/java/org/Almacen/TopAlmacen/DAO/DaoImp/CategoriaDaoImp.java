package org.Almacen.TopAlmacen.DAO.DaoImp;

import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
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
        return _entityManager.createQuery("SELECT c FROM Categoria c order by c.id asc ", Categoria.class).getResultList();
    }

    @Override
    public List<Categoria> getAllByEstadoActivo() {
        return _entityManager.createQuery("SELECT c FROM Categoria c WHERE c.estado='Activo'", Categoria.class).getResultList();
    }

    @Override
    public Categoria getById(int id) {
        var query = _entityManager.createQuery(
                "SELECT c FROM Categoria c LEFT JOIN FETCH c.productos WHERE c.id = :id", Categoria.class
        );
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public Categoria create(Categoria c) {
        _entityManager.persist(c);
        return c;
    }

    @Override
    public void cambioEstado(int id, String estado) {
        var query = _entityManager.createQuery(
                "UPDATE Categoria c SET c.estado = :estado WHERE c.id = :id"
        );
        query.setParameter("estado", estado);
        query.setParameter("id", id);
        query.executeUpdate();
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
