package org.Almacen.TopAlmacen.Services;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.Almacen.TopAlmacen.DAO.IMarcaDao;
import org.Almacen.TopAlmacen.DTO.Categoria.CategoriaDto;
import org.Almacen.TopAlmacen.DTO.Categoria.CreateCategoriaDto;
import org.Almacen.TopAlmacen.DTO.Marca.CreateMarcaDto;
import org.Almacen.TopAlmacen.DTO.Marca.MarcaDto;
import org.Almacen.TopAlmacen.DTO.Marca.UpdateMarcaDto;
import org.Almacen.TopAlmacen.Mappers.CategoriaMapper;
import org.Almacen.TopAlmacen.Mappers.MarcaMapper;
import org.Almacen.TopAlmacen.Model.Categoria;
import org.Almacen.TopAlmacen.Model.Marca;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
@LocalBean
public class MarcaService implements Serializable {

    @Inject
    private IMarcaDao iMarcaDao;

    @Transactional
    public List<MarcaDto> getAllMarca() {
        List<Marca> marcas = iMarcaDao.getAll();
        return marcas.stream()
                .map(c -> new MarcaDto(c.getId(), c.getNombre(), c.getEstado(), c.getFechaRegistro()))
                .collect(Collectors.toList());
    }

    @Transactional
    public Marca createMarca(CreateMarcaDto createMarcaDto) {
        var Marca = MarcaMapper.toMarcaFromCreate(createMarcaDto);
        return iMarcaDao.create(Marca);
    }

    @Transactional
    public MarcaDto getMarcaById(int id){
        var marca = iMarcaDao.getById(id);
        return MarcaMapper.toDto(marca);
    }


    @Transactional
    public Marca updateMarca(UpdateMarcaDto updateMarcaDto, int id){
        return iMarcaDao.update(updateMarcaDto,id);
    }

    @Transactional
    public void cambiarEstado(int id, String estado){
        iMarcaDao.cambiarMarca(id,estado);
    }





}
