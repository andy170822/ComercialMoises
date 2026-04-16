package com.comercialmoises.service;

import com.comercialmoises.dto.ProductoDTO;
import com.comercialmoises.exception.ResourceNotFoundException;
import com.comercialmoises.mappers.ProductoMapper;
import com.comercialmoises.model.Producto;
import com.comercialmoises.repository.ProductoRepository;
import com.comercialmoises.services.impl.ProductoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("ProductoService Unit Tests")
class ProductoServiceTest {

    @Mock
    private ProductoRepository repository;

    @Mock
    private ProductoMapper mapper;

    @InjectMocks
    private ProductoServiceImpl service;

    private Producto producto;
    private ProductoDTO productoDTO;

    @BeforeEach
    void setUp() {
        producto = new Producto();
        producto.setIdProducto(1);
        producto.setNombreProducto("Laptop");
        producto.setPrecioVenta(new BigDecimal("1500.00"));
        producto.setEstado(true);

        productoDTO = new ProductoDTO();
        productoDTO.setIdProducto(1);
        productoDTO.setNombreProducto("Laptop");
        productoDTO.setPrecioVenta(new BigDecimal("1500.00"));
        productoDTO.setEstado(true);
    }

    @Test
    @DisplayName("Should return all products")
    void testListar() {
        when(repository.findAll()).thenReturn(Collections.singletonList(producto));
        when(mapper.toDTO(any(Producto.class))).thenReturn(productoDTO);

        List<ProductoDTO> result = service.listar();

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals("Laptop", result.get(0).getNombreProducto());
    }

    @Test
    @DisplayName("Should find product by ID")
    void testBuscarPorId() {
        when(repository.findById(1)).thenReturn(Optional.of(producto));
        when(mapper.toDTO(any(Producto.class))).thenReturn(productoDTO);

        ProductoDTO result = service.buscarPorId(1);

        assertNotNull(result);
        assertEquals("Laptop", result.getNombreProducto());
    }

    @Test
    @DisplayName("Should throw ResourceNotFoundException when product not found")
    void testBuscarPorIdNotFound() {
        when(repository.findById(1)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> service.buscarPorId(1));
    }

    @Test
    @DisplayName("Should save a new product")
    void testGuardar() {
        when(mapper.toEntity(any(ProductoDTO.class))).thenReturn(producto);
        when(repository.save(any(Producto.class))).thenReturn(producto);
        when(mapper.toDTO(any(Producto.class))).thenReturn(productoDTO);

        ProductoDTO result = service.guardar(productoDTO);

        assertNotNull(result);
        assertEquals("Laptop", result.getNombreProducto());
        verify(repository, times(1)).save(any(Producto.class));
    }

    @Test
    @DisplayName("Should delete (deactivate) a product")
    void testEliminar() {
        when(repository.findById(1)).thenReturn(Optional.of(producto));

        service.eliminar(1);

        assertFalse(producto.getEstado());
        verify(repository, times(1)).save(producto);
    }
}
