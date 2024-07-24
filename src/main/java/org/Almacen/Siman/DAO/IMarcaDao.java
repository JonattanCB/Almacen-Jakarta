package org.Almacen.Siman.DAO;

import org.Almacen.Siman.DTO.Marca.UpdateMarcaDto;
import org.Almacen.Siman.Model.Marca;

import java.util.List;

public interface IMarcaDao {
    List<Marca> getAll();

    List<Marca> getAllEstadoActivo();

    Marca getById(int id);

    Marca create(Marca c);

    Marca update(UpdateMarcaDto c, int id);

    Marca delete(int id);

    void cambiarMarca(int id, String estado);
}
