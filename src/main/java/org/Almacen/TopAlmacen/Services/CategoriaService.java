package org.Almacen.TopAlmacen.Services;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import org.Almacen.TopAlmacen.DAO.ICategoriaDao;

import java.util.List;

@Stateless
public class CategoriaService {

    @Inject
    private ICategoriaDao iCategoriaDao;
    public List<Categoria> getAll() {

    }
}
