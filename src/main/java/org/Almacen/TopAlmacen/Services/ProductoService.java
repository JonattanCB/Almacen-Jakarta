package org.Almacen.TopAlmacen.Services;


import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.Almacen.TopAlmacen.DAO.IProductoDao;
import org.Almacen.TopAlmacen.DTO.Categoria.CategoriaDto;
import org.Almacen.TopAlmacen.Model.Categoria;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
@LocalBean
public class ProductoService implements Serializable {

    @Inject
    private IProductoDao iProductoDao;


}
