package org.Almacen.Siman.Services;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.Almacen.Siman.DAO.IEmpresaDao;
import org.Almacen.Siman.DTO.Empresa.CreateEmpresaDto;
import org.Almacen.Siman.DTO.Empresa.EmpresaDto;
import org.Almacen.Siman.DTO.Empresa.UpdateEmpresaDto;
import org.Almacen.Siman.Mappers.EmpresaMapper;
import org.Almacen.Siman.Model.*;

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
        return empresas.stream().map(EmpresaMapper::toDto).collect(Collectors.toList());
    }

    @Transactional
    public List<EmpresaDto> getAllActiveEstado() {
        var empresas = iEmpresaDao.getAllActiveEstado();
        return empresas.stream().map(EmpresaMapper::toDto).collect(Collectors.toList());
    }


    @Transactional
    public EmpresaDto getEmpresa(String NroRuc) {
        var empresa = iEmpresaDao.getById(NroRuc);
        return EmpresaMapper.toDto(empresa);
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
        if (iEmpresaDao.isEmpresaAsociada(NroRuc)) {
            iEmpresaDao.changeState(NroRuc, "INACTIVO");
            return null;
        }
        return iEmpresaDao.delete(NroRuc);
    }

    @Transactional
    public boolean isEmpresaAsociada(String NroRuc) {
        return iEmpresaDao.isEmpresaAsociada(NroRuc);
    }

    @Transactional
    public void changeActive(String NroRuc) {
        iEmpresaDao.changeState(NroRuc, "ACTIVO");
    }

    @Transactional
    public boolean EmpresaExists(String NroRuc) {
        return iEmpresaDao.exist(NroRuc);
    }
}
