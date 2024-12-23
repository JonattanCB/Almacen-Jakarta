package org.Almacen.Siman.Mappers;

import org.Almacen.Siman.DTO.Empresa.CreateEmpresaDto;
import org.Almacen.Siman.DTO.Empresa.EmpresaDto;
import org.Almacen.Siman.DTO.Empresa.UpdateEmpresaDto;
import org.Almacen.Siman.Model.Empresa;

import java.util.List;
import java.util.stream.Collectors;

public class EmpresaMapper {
    public static EmpresaDto toDto(Empresa empresa) {
        return new EmpresaDto(empresa.getNroRUC(), empresa.getNombre(), empresa.getTipoEmpresa(), empresa.getDireccion(), empresa.getEstado());
    }

    public static Empresa toEntity(EmpresaDto dto) {
        return new Empresa(dto.getNroRUC(), dto.getNombre(), dto.getTipoEmpresa(), dto.getDireccion(), dto.getEstado());
    }

    public static List<EmpresaDto> toDTOList(List<Empresa> empresas) {
        return empresas.stream()
                .map(EmpresaMapper::toDto)
                .collect(Collectors.toList());
    }

    public static Empresa toEmpresaFromCreate(CreateEmpresaDto dto) {
        Empresa empresa = new Empresa();
        empresa.setNroRUC(dto.getNroRUC());
        empresa.setNombre(dto.getNombre());
        empresa.setDireccion(dto.getDireccion());
        empresa.setTipoEmpresa(dto.getTipoEmpresa());
        empresa.setEstado(dto.getEstado());
        return empresa;
    }

    public static Empresa toEmpresaFromUpdate(UpdateEmpresaDto dto) {
        Empresa empresa = new Empresa();
        empresa.setNroRUC(dto.getNroRUC());
        empresa.setNombre(dto.getNombre());
        empresa.setDireccion(dto.getDireccion());
        empresa.setTipoEmpresa(dto.getTipoEmpresa());
        empresa.setEstado(dto.getEstado());
        return empresa;
    }


}

