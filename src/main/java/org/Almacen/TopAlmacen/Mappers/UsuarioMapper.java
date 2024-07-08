package org.Almacen.TopAlmacen.Mappers;


import org.Almacen.TopAlmacen.DTO.Usuario.CreateUsuarioDto;
import org.Almacen.TopAlmacen.DTO.Usuario.UpdateUsuarioDto;
import org.Almacen.TopAlmacen.DTO.Usuario.UsuarioDto;
import org.Almacen.TopAlmacen.Model.Usuario;

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

    public static Usuario toUsuarioFromUpdate(UpdateUsuarioDto dto) {
        Usuario usuario = new Usuario();
        usuario.setCorreo(dto.getCorreo());
        usuario.setContra(dto.getContra());
        usuario.setNombres(dto.getNombres());
        usuario.setApellidos(dto.getApellidos());
        usuario.setEstado(dto.getEstado());
        usuario.setUnidadDependencia(dto.getUnidad());
        return usuario;
    }


}
