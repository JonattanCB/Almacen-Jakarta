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

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
@LocalBean
public class UsuarioService  implements Serializable {

    @Inject
    private IUsuarioDao iUsuarioDao;


    @Transactional
    public List<UsuarioDto> getAllUsuario() {
        List<Usuario> usuarios = iUsuarioDao.getAll();
        return usuarios.stream()
                .map(c -> new UsuarioDto(c.getId(),c.getCorreo(),c.getContra(),c.getNombres(),c.getApellidos(),c.getEstado(),c.getRol(),c.getFechaRegistro()))
                .collect(Collectors.toList());
    }

    @Transactional
    public Usuario createUsuario(CreateUsuarioDto createUsuarioDto) {
        var usuario = UsuarioMapper.toUsuarioFromCreate(createUsuarioDto);
        return iUsuarioDao.create(usuario);
    }

    @Transactional
    public UsuarioDto getUsuarioById(int id){
        var usuario = iUsuarioDao.getById(id);
        return UsuarioMapper.toDto(usuario);
    }

    @Transactional
    public Usuario updateUsuario(UpdateUsuarioDto updateUsuarioDto, int id){
        return iUsuarioDao.update(updateUsuarioDto,id);
    }

    @Transactional
    public void cambiarEstado(int id, String estado){
        iUsuarioDao.cambiarMarca(id,estado);
    }



}
