package com.comercialmoises.services.impl;

import com.comercialmoises.dto.CategoriaDTO;
import com.comercialmoises.exception.ResourceNotFoundException;
import com.comercialmoises.mappers.CategoriaMapper;
import com.comercialmoises.model.Categoria;
import com.comercialmoises.repository.CategoriaRepository;
import com.comercialmoises.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    private CategoriaRepository repository;

    @Autowired
    private CategoriaMapper mapper;

    @Override
    public List<CategoriaDTO> listar() {
        return repository.findAll().stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public CategoriaDTO buscarPorId(Integer id) {
        if (id == null)
            return null;
        return repository.findById(id).map(mapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria no encontrada con ID: " + id));
    }

    @Override
    public CategoriaDTO guardar(CategoriaDTO dto) {
        if (dto == null)
            throw new IllegalArgumentException("DTO cannot be null");
        Categoria categoria = mapper.toEntity(dto);
        return mapper.toDTO(repository.save(categoria));
    }

    @Override
    public CategoriaDTO actualizar(Integer id, CategoriaDTO dto) {
        if (id == null || dto == null)
            throw new IllegalArgumentException("ID and DTO cannot be null");
        repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria no encontrada con ID: " + id));
        Categoria categoria = mapper.toEntity(dto);
        categoria.setIdCategoria(id);
        return mapper.toDTO(repository.save(categoria));
    }

    @Override
    public void eliminar(Integer id) {
        if (id == null)
            return;
        Categoria categoria = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria no encontrada con ID: " + id));
        categoria.setEstado(false);
        repository.save(categoria);
    }
}
