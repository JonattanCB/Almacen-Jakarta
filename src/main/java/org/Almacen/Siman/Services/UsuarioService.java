package org.Almacen.Siman.Services;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.Almacen.Siman.DAO.IUsuarioDao;
import org.Almacen.Siman.DTO.Usuario.CreateUsuarioDto;
import org.Almacen.Siman.DTO.Usuario.UpdateUsuarioDto;
import org.Almacen.Siman.DTO.Usuario.UsuarioDto;
import org.Almacen.Siman.Mappers.UsuarioMapper;
import org.Almacen.Siman.Model.Usuario;
import org.Almacen.Siman.Util.PasswordUtil;

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
        updateUsuarioDto.setContra(PasswordUtil.hashPassword(updateUsuarioDto.getContra()));
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

    @Transactional
    public void cambiarContrasenia(String password, int id){
        String MD5 = PasswordUtil.hashPassword(password);
        iUsuarioDao.cambiarPasswor(MD5,id);
    }

    @Transactional
    public int cantidadUsuarioDependencia(int dependencia){
        return iUsuarioDao.cantidadUsuarioDependencia(dependencia);
    }

    @Transactional
    public  int cantidadUsuarioDependenciaStatus(int dependencia, String status){
        return iUsuarioDao.cantidadUsuariosDependenciaStatus(dependencia,status);
    }

    @Transactional
    public int cantidadUsuarios(){
        return  iUsuarioDao.cantidadUsuarios();
    }

    @Transactional
    public int cantidadUsuariosStatus(String status){
        return  iUsuarioDao.cantidadUsuariosStatus(status);
    }

}
