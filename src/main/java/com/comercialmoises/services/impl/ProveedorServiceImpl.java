package com.comercialmoises.services.impl;

import com.comercialmoises.dto.ProveedorDTO;
import com.comercialmoises.exception.ResourceNotFoundException;
import com.comercialmoises.mappers.ProveedorMapper;
import com.comercialmoises.model.Proveedor;
import com.comercialmoises.repository.ProveedorRepository;
import com.comercialmoises.services.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProveedorServiceImpl implements ProveedorService {

    @Autowired
    private ProveedorRepository repository;

    @Autowired
    private ProveedorMapper mapper;

    @Override
    public List<ProveedorDTO> listar() {
        return repository.findAll().stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public ProveedorDTO buscarPorId(Integer id) {
        if (id == null)
            return null;
        return repository.findById(id).map(mapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Proveedor no encontrado con ID: " + id));
    }

    @Override
    public ProveedorDTO guardar(ProveedorDTO dto) {
        Proveedor proveedor = mapper.toEntity(dto);
        return mapper.toDTO(repository.save(proveedor));
    }

    @Override
    public ProveedorDTO actualizar(Integer id, ProveedorDTO dto) {
        if (id == null || dto == null)
            throw new IllegalArgumentException("ID and DTO cannot be null");
        repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Proveedor no encontrado con ID: " + id));
        Proveedor proveedor = mapper.toEntity(dto);
        proveedor.setIdProveedor(id);
        return mapper.toDTO(repository.save(proveedor));
    }

    @Override
    public void eliminar(Integer id) {
        if (id == null)
            return;
        Proveedor proveedor = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Proveedor no encontrado con ID: " + id));
        proveedor.setEstado(false);
        repository.save(proveedor);
    }
}
