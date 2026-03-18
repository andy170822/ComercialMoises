package com.comercialmoises.services.impl;

import com.comercialmoises.dto.CompraDTO;
import com.comercialmoises.dto.DetalleCompraDTO;
import com.comercialmoises.exception.ResourceNotFoundException;
import com.comercialmoises.mappers.CompraMapper;
import com.comercialmoises.model.*;
import com.comercialmoises.repository.*;
import com.comercialmoises.services.CompraService;
import com.comercialmoises.service.AuditoriaLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompraServiceImpl implements CompraService {

    @Autowired
    private CompraRepository repository;

    @Autowired
    private DetalleCompraRepository detalleRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CompraMapper mapper;

    @Autowired
    private AuditoriaLogService auditService;

    @Override
    public List<CompraDTO> listar() {
        return repository.findAll().stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public CompraDTO buscarPorId(Integer id) {
        Compra compra = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Compra no encontrada"));
        CompraDTO dto = mapper.toDTO(compra);
        // Load details if needed
        return dto;
    }

    @Override
    @Transactional
    public CompraDTO guardar(CompraDTO dto) {
        // Basic logic to save a purchase and update stock
        Compra compra = new Compra();
        Proveedor prov = new Proveedor();
        prov.setIdProveedor(dto.getIdProveedor());
        compra.setProveedor(prov);
        Usuario usu = new Usuario();
        usu.setIdUsuario(dto.getIdUsuario());
        compra.setUsuario(usu);
        compra.setTipoComprobante(dto.getTipoComprobante());
        compra.setSerie(dto.getSerie());
        compra.setNumeroComprobante(dto.getNumeroComprobante());
        compra.setTotal(dto.getTotal());

        Compra saved = repository.save(compra);

        for (DetalleCompraDTO d : dto.getDetalles()) {
            DetalleCompra detail = new DetalleCompra();
            detail.setCompra(saved);
            Producto prod = productoRepository.findById(d.getIdProducto())
                    .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado"));
            detail.setProducto(prod);
            detail.setCantidad(d.getCantidad());
            detail.setPrecioUnitario(d.getPrecioUnitario());
            detail.setImporte(d.getImporte());
            detalleRepository.save(detail);

            // Update Stock
            prod.setStockActual(prod.getStockActual() + d.getCantidad());
            productoRepository.save(prod);
        }

        auditService.registrarLog("Admin", "REGISTRO", "COMPRAS", "Compra registrada - Total: " + compra.getTotal(),
                "127.0.0.1");

        return mapper.toDTO(saved);
    }
}
