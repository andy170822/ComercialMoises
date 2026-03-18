package com.comercialmoises.services.impl;

import com.comercialmoises.dto.RolDTO;
import com.comercialmoises.exception.ResourceNotFoundException;
import com.comercialmoises.mappers.RolMapper;
import com.comercialmoises.model.Rol;
import com.comercialmoises.repository.RolRepository;
import com.comercialmoises.services.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RolServiceImpl implements RolService {

    @Autowired
    private RolRepository repository;

    @Autowired
    private RolMapper mapper;

    @Override
    public List<RolDTO> listar() {
        return repository.findAll().stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public RolDTO buscarPorId(Integer id) {
        if (id == null)
            return null;
        return repository.findById(id).map(mapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Rol no encontrado con ID: " + id));
    }

    @Override
    public RolDTO guardar(RolDTO dto) {
        Rol rol = mapper.toEntity(dto);
        return mapper.toDTO(repository.save(rol));
    }

    @Override
    public RolDTO actualizar(Integer id, RolDTO dto) {
        if (id == null || dto == null)
            throw new IllegalArgumentException("ID and DTO cannot be null");
        repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Rol no encontrado con ID: " + id));
        Rol rol = mapper.toEntity(dto);
        rol.setIdRol(id);
        return mapper.toDTO(repository.save(rol));
    }

    @Override
    public void eliminar(Integer id) {
        if (id == null)
            return;
        Rol rol = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rol no encontrado con ID: " + id));
        rol.setEstado(false);
        repository.save(rol);
    }
}
