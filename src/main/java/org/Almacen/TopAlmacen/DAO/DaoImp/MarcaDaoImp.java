package org.Almacen.TopAlmacen.DAO.DaoImp;

import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.Almacen.TopAlmacen.DAO.IMarcaDao;
import org.Almacen.TopAlmacen.DTO.Marca.UpdateMarcaDto;
import org.Almacen.TopAlmacen.Model.Marca;

import java.util.List;

@Named
public class MarcaDaoImp implements IMarcaDao {
    @PersistenceContext(name = "YourPU")
    private EntityManager _entityManager;

    @Override
    public List<Marca> getAll() {
        return _entityManager.createQuery("SELECT m FROM Marca m order by  m.id asc ", Marca.class).getResultList();
    }

    @Override
    public Marca getById(int id) {
        return _entityManager.find(Marca.class, id);
    }

    @Override
    public Marca create(Marca c) {

        _entityManager.persist(c);
        return c;
    }

    @Override
    public Marca update(UpdateMarcaDto c, int id) {
        var marca = _entityManager.find(Marca.class, id);
        if (marca != null) {
            marca.setNombre(c.getNombre());
        }
        return null;
    }

    @Override
    public Marca delete(int id) {
        return null;
    }

    @Override
    public void cambiarMarca(int id, String estado) {
        var query = _entityManager.createQuery(
                "UPDATE Marca m SET m.estado = :estado WHERE m.id = :id"
        );
        query.setParameter("estado", estado);
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
