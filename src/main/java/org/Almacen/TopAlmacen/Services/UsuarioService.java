package org.Almacen.TopAlmacen.Services;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.Almacen.TopAlmacen.DAO.IUsuarioDao;
import org.Almacen.TopAlmacen.DTO.Usuario.CreateUsuarioDto;
import org.Almacen.TopAlmacen.DTO.Usuario.UpdateUsuarioDto;
import org.Almacen.TopAlmacen.DTO.Usuario.UsuarioDto;
import org.Almacen.TopAlmacen.Mappers.UsuarioMapper;
import org.Almacen.TopAlmacen.Model.Usuario;
import org.Almacen.TopAlmacen.Util.PasswordUtil;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
@LocalBean
public class UsuarioService implements Serializable {

    @Inject
    private IUsuarioDao iUsuarioDao;


    @Transactional
    public List<UsuarioDto> getAllUsuario() {
        List<Usuario> usuarios = iUsuarioDao.getAll();
        return usuarios.stream().map(UsuarioMapper::toDto).collect(Collectors.toList());
    }

    @Transactional
    public Usuario createUsuario(CreateUsuarioDto createUsuarioDto) {
        if(iUsuarioDao.existeEmail(createUsuarioDto.getCorreo())){
            return null;
        }else{
            var usuario = UsuarioMapper.toUsuarioFromCreate(createUsuarioDto);
            usuario.setContra(PasswordUtil.hashPassword(usuario.getContra()));
            return iUsuarioDao.create(usuario);
        }
    }

    @Transactional
    public UsuarioDto getUsuarioById(int id) {
        var usuario = iUsuarioDao.getById(id);
        return UsuarioMapper.toDto(usuario);
    }

    @Transactional
    public Usuario updateUsuario(UpdateUsuarioDto updateUsuarioDto, int id) {
        var usuarioExistente = getUsuarioById(id);
        if (usuarioExistente != null && !usuarioExistente.getCorreo().equals(updateUsuarioDto.getCorreo())) {
            if (iUsuarioDao.existeEmail(updateUsuarioDto.getCorreo())) {
                return null;
            }
        }
        return iUsuarioDao.update(updateUsuarioDto, id);
    }

    @Transactional
    public void cambiarEstado(int id, String estado) {
        iUsuarioDao.cambiarEstado(id, estado);
    }

    @Transactional
    public UsuarioDto checkUsuario(String email, String password) {
        var usuario = iUsuarioDao.checkLogin(email, password);
        if (usuario != null) {
            return UsuarioMapper.toDto(usuario);
        } else {
            return null;
        }
    }


}
