package org.Almacen.TopAlmacen.Services;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import org.Almacen.TopAlmacen.DAO.IAccesoDao;
import org.Almacen.TopAlmacen.DTO.Acceso.AccesoDto;

import java.util.List;

@Stateless
@LocalBean
public class AccesoService {

    @Inject
    private IAccesoDao iAccesoDao;

    public List<AccesoDto> listarAccesos(int idRol) {
        return null;
    }

    /*
     List<Categoria> categorias = iCategoriaDao.getAll();
        return categorias.stream().map(CategoriaMapper::toDto).collect(Collectors.toList());
     */


}
