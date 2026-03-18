package com.comercialmoises.mappers;

import com.comercialmoises.dto.UsuarioDTO;
import com.comercialmoises.model.Usuario;
import com.comercialmoises.model.Rol;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {
    public UsuarioDTO toDTO(Usuario usuario) {
        if (usuario == null)
            return null;
        return new UsuarioDTO(
                usuario.getIdUsuario(),
                usuario.getRol() != null ? usuario.getRol().getIdRol() : null,
                usuario.getRol() != null ? usuario.getRol().getNombreRol() : null,
                usuario.getNombres(),
                usuario.getApellidos(),
                usuario.getDni(),
                usuario.getCorreo(),
                usuario.getUsername(),
                null, // Do not return password
                usuario.getEstado(),
                usuario.getFechaRegistro());
    }

    public Usuario toEntity(UsuarioDTO dto) {
        if (dto == null)
            return null;
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(dto.getIdUsuario());
        usuario.setNombres(dto.getNombres());
        usuario.setApellidos(dto.getApellidos());
        usuario.setDni(dto.getDni());
        usuario.setCorreo(dto.getCorreo());
        usuario.setUsername(dto.getUsername());
        usuario.setPasswordHash(dto.getPassword());
        usuario.setEstado(dto.getEstado());
        usuario.setFechaRegistro(dto.getFechaRegistro());
        if (dto.getIdRol() != null) {
            Rol rol = new Rol();
            rol.setIdRol(dto.getIdRol());
            usuario.setRol(rol);
        }
        return usuario;
    }
}
