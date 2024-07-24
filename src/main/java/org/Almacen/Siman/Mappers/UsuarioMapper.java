package org.Almacen.Siman.Mappers;


import org.Almacen.Siman.DTO.Usuario.CreateUsuarioDto;
import org.Almacen.Siman.DTO.Usuario.UsuarioDto;
import org.Almacen.Siman.Model.Usuario;

import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UsuarioMapper {

    public static UsuarioDto toDto(Usuario usuario) {
        return new UsuarioDto(usuario.getId(), usuario.getCorreo(), usuario.getContra(), usuario.getNombres(), usuario.getApellidos(), usuario.getEstado(), usuario.getFechaRegistro(), usuario.getUnidadDependencia());
    }

    public static Usuario toUsuario(UsuarioDto dto) {
        return new Usuario(dto.getId(), dto.getCorreo(), dto.getContra(), dto.getNombres(), dto.getApellidos(), dto.getEstado(), dto.getFechaRegistro(), dto.getUnidad());
    }

    public static Usuario toUsuarioFromCreate(CreateUsuarioDto dto) {
        Usuario usuario = new Usuario();
        usuario.setCorreo(dto.getCorreo());
        usuario.setContra(dto.getContra());
        usuario.setNombres(dto.getNombres());
        usuario.setApellidos(dto.getApellidos());
        usuario.setEstado(dto.getEstado());
        usuario.setUnidadDependencia(dto.getUnidad());
        return usuario;
    }

    public static String toConcatuser(Usuario usuario) {
        return Stream.of(usuario.getApellidos(), usuario.getNombres())
                .filter(Objects::nonNull)
                .map(String::valueOf)
                .filter(s -> !s.trim().isEmpty())
                .collect(Collectors.joining(" "));

    }
}
