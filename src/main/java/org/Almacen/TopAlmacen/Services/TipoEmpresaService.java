package org.Almacen.TopAlmacen.Services;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.Almacen.TopAlmacen.DAO.ITipoEmpresaDao;
import org.Almacen.TopAlmacen.DTO.Categoria.CategoriaDto;
import org.Almacen.TopAlmacen.DTO.TipoEmpresa.TipoEmpresaDto;
import org.Almacen.TopAlmacen.Model.Categoria;
import org.Almacen.TopAlmacen.Model.TipoEmpresa;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
@LocalBean
public class TipoEmpresaService implements Serializable {
    @Inject
    private ITipoEmpresaDao iTipoEmpresaDao;

    @Transactional
    public List<TipoEmpresaDto> getAllTipoEmpresa() {
        List<TipoEmpresa> tipoEmpresas = iTipoEmpresaDao.getAll();
        return tipoEmpresas.stream()
                .map(c -> new TipoEmpresaDto(c.getId(), c.getNombre(), c.getAbrev()))
                .collect(Collectors.toList());
    }

    @Transactional
    public TipoEmpresaDto getTipoEmpesa(int id) {
        var tipoEmpresa = iTipoEmpresaDao.getById(id);
        return  new TipoEmpresaDto(tipoEmpresa.getId(), tipoEmpresa.getNombre(), tipoEmpresa.getAbrev());
    }

}
