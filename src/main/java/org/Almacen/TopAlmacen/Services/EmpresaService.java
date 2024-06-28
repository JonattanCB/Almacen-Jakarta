package org.Almacen.TopAlmacen.Services;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.Almacen.TopAlmacen.DAO.IEmpresaDao;
import org.Almacen.TopAlmacen.DTO.Empresa.CreateEmpresaDto;
import org.Almacen.TopAlmacen.DTO.Empresa.EmpresaDto;
import org.Almacen.TopAlmacen.DTO.Empresa.UpdateEmpresaDto;
import org.Almacen.TopAlmacen.Mappers.EmpresaMapper;
import org.Almacen.TopAlmacen.Model.*;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
@LocalBean
public class EmpresaService implements Serializable {
    @Inject
    private IEmpresaDao iEmpresaDao;

    @Transactional
    public List<EmpresaDto> getAllEmpresa() {
        List<Empresa> empresas = iEmpresaDao.getAll();
        return empresas.stream()
                .map(c -> new EmpresaDto(c.getNroRUC(), c.getNombre(), c.getTipoEmpresa(), c.getDireccion()))
                .collect(Collectors.toList());
    }

    @Transactional
    public EmpresaDto getEmpresa(String NroRuc) {
        var empresa = iEmpresaDao.getById(NroRuc);
        return  new EmpresaDto(empresa.getNroRUC(), empresa.getNombre(),  empresa.getTipoEmpresa(), empresa.getDireccion());
    }

    @Transactional
    public Empresa createEmpresa(CreateEmpresaDto createEmpresaDto) {
        var Empresa = EmpresaMapper.toEmpresaFromCreate(createEmpresaDto);
        return iEmpresaDao.create(Empresa);
    }

    @Transactional
    public Empresa updateEmpresa(String NroRuc, UpdateEmpresaDto updateEmpresaDto) {
        return iEmpresaDao.update(updateEmpresaDto, NroRuc);
    }

    @Transactional
    public Empresa deleteEmpresa(String NroRuc) {
        return iEmpresaDao.delete(NroRuc);
    }

}
