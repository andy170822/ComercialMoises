package com.comercialmoises.services.impl;

import com.comercialmoises.dto.ClienteDTO;
import com.comercialmoises.exception.ResourceNotFoundException;
import com.comercialmoises.mappers.ClienteMapper;
import com.comercialmoises.model.Cliente;
import com.comercialmoises.repository.ClienteRepository;
import com.comercialmoises.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Autowired
    private ClienteMapper mapper;

    @Override
    public List<ClienteDTO> listar() {
        return repository.findAll().stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public ClienteDTO buscarPorId(Integer id) {
        if (id == null)
            return null;
        return repository.findById(id).map(mapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con ID: " + id));
    }

    @Override
    public ClienteDTO guardar(ClienteDTO dto) {
        if (dto == null)
            throw new IllegalArgumentException("DTO cannot be null");
        Cliente cliente = mapper.toEntity(dto);
        return mapper.toDTO(repository.save(cliente));
    }

    @Override
    public ClienteDTO actualizar(Integer id, ClienteDTO dto) {
        if (id == null || dto == null)
            throw new IllegalArgumentException("ID and DTO cannot be null");
        repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con ID: " + id));
        Cliente cliente = mapper.toEntity(dto);
        cliente.setIdCliente(id);
        return mapper.toDTO(repository.save(cliente));
    }

    @Override
    public void eliminar(Integer id) {
        if (id == null)
            return;
        Cliente cliente = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con ID: " + id));
        cliente.setEstado(false);
        repository.save(cliente);
    }
}
