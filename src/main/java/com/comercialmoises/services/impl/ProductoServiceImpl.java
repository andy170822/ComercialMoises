package com.comercialmoises.services.impl;

import com.comercialmoises.dto.ProductoDTO;
import com.comercialmoises.exception.ResourceNotFoundException;
import com.comercialmoises.mappers.ProductoMapper;
import com.comercialmoises.model.Producto;
import com.comercialmoises.repository.ProductoRepository;
import com.comercialmoises.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository repository;

    @Autowired
    private ProductoMapper mapper;

    @Override
    public List<ProductoDTO> listar() {
        return repository.findAll().stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public ProductoDTO buscarPorId(Integer id) {
        if (id == null)
            return null;
        return repository.findById(id).map(mapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con ID: " + id));
    }

    @Override
    public ProductoDTO guardar(ProductoDTO dto) {
        Producto producto = mapper.toEntity(dto);
        return mapper.toDTO(repository.save(producto));
    }

    @Override
    public ProductoDTO actualizar(Integer id, ProductoDTO dto) {
        if (id == null || dto == null)
            throw new IllegalArgumentException("ID and DTO cannot be null");
        repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con ID: " + id));
        Producto producto = mapper.toEntity(dto);
        producto.setIdProducto(id);
        return mapper.toDTO(repository.save(producto));
    }

    @Override
    public void eliminar(Integer id) {
        if (id == null)
            return;
        Producto producto = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con ID: " + id));
        producto.setEstado(false);
        repository.save(producto);
    }
}
