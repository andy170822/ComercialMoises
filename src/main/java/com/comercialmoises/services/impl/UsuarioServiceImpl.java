package com.comercialmoises.services.impl;

import com.comercialmoises.dto.UsuarioDTO;
import com.comercialmoises.exception.ResourceNotFoundException;
import com.comercialmoises.mappers.UsuarioMapper;
import com.comercialmoises.model.Usuario;
import com.comercialmoises.repository.UsuarioRepository;
import com.comercialmoises.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private UsuarioMapper mapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<UsuarioDTO> listar() {
        return repository.findAll().stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public UsuarioDTO buscarPorId(Integer id) {
        if (id == null)
            return null;
        return repository.findById(id).map(mapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con ID: " + id));
    }

    @Override
    public UsuarioDTO guardar(UsuarioDTO dto) {
        Usuario usuario = mapper.toEntity(dto);
        usuario.setPasswordHash(passwordEncoder.encode(dto.getPassword()));
        return mapper.toDTO(repository.save(usuario));
    }

    @Override
    public UsuarioDTO actualizar(Integer id, UsuarioDTO dto) {
        if (id == null || dto == null)
            throw new IllegalArgumentException("ID and DTO cannot be null");
        Usuario existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con ID: " + id));
        Usuario usuario = mapper.toEntity(dto);
        usuario.setIdUsuario(id);
        if (dto.getPassword() != null && !dto.getPassword().isEmpty()) {
            usuario.setPasswordHash(passwordEncoder.encode(dto.getPassword()));
        } else {
            usuario.setPasswordHash(existing.getPasswordHash());
        }
        return mapper.toDTO(repository.save(usuario));
    }

    @Override
    public void eliminar(Integer id) {
        if (id == null)
            return;
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con ID: " + id));
        usuario.setEstado(false);
        repository.save(usuario);
    }
}
