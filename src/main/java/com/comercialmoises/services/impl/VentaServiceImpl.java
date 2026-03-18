package com.comercialmoises.services.impl;

import com.comercialmoises.dto.VentaDTO;
import com.comercialmoises.dto.DetalleVentaDTO;
import com.comercialmoises.exception.ResourceNotFoundException;
import com.comercialmoises.mappers.VentaMapper;
import com.comercialmoises.model.*;
import com.comercialmoises.repository.*;
import com.comercialmoises.services.VentaService;
import com.comercialmoises.service.AuditoriaLogService;
import com.comercialmoises.service.FacturacionElectronicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VentaServiceImpl implements VentaService {

    @Autowired
    private VentaRepository repository;

    @Autowired
    private DetalleVentaRepository detalleRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private VentaMapper mapper;

    @Autowired
    private AuditoriaLogService auditService;

    @Autowired
    private FacturacionElectronicaService facturacionService;

    @Override
    public List<VentaDTO> listar() {
        return repository.findAll().stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public VentaDTO buscarPorId(Integer id) {
        Venta venta = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Venta no encontrada"));
        VentaDTO dto = mapper.toDTO(venta);
        List<DetalleVentaDTO> details = detalleRepository.findByVenta(venta).stream()
                .map(mapper::toDetalleDTO).collect(Collectors.toList());
        dto.setDetalles(details);
        return dto;
    }

    @Override
    @Transactional
    public VentaDTO guardar(VentaDTO dto) {
        Venta venta = new Venta();
        Cliente cli = new Cliente();
        cli.setIdCliente(dto.getIdCliente());
        venta.setCliente(cli);
        Usuario usu = new Usuario();
        usu.setIdUsuario(dto.getIdUsuario());
        venta.setUsuario(usu);
        venta.setTipoComprobante(dto.getTipoComprobante());
        venta.setSerie(dto.getSerie());
        venta.setNumeroComprobante(dto.getNumeroComprobante());
        venta.setSubTotal(dto.getSubTotal());
        venta.setIgv(dto.getIgv());
        venta.setTotal(dto.getTotal());

        Venta saved = repository.save(venta);

        for (DetalleVentaDTO d : dto.getDetalles()) {
            DetalleVenta detail = new DetalleVenta();
            detail.setVenta(saved);
            Producto prod = productoRepository.findById(d.getIdProducto())
                    .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado"));
            detail.setProducto(prod);
            detail.setCantidad(d.getCantidad());
            detail.setPrecioUnitario(d.getPrecioUnitario());
            detail.setImporte(d.getImporte());
            detalleRepository.save(detail);

            // Validate and Update Stock
            if (prod.getStockActual() < d.getCantidad()) {
                throw new RuntimeException("Stock insuficiente para el producto con ID: " + prod.getIdProducto());
            }
            prod.setStockActual(prod.getStockActual() - d.getCantidad());
            productoRepository.save(prod);
        }

        // Simulate Electronic Invoicing (SUNAT/SRI/AFIP integration)
        ComprobanteElectronico comprobanteDesc = facturacionService.procesarFacturacionVenta(venta);
        System.out.println("---- FACTURACIÓN ELECTRÓNICA SIMULADA ----");
        System.out.println("Comprobante: " + comprobanteDesc.getVenta().getSerie() + "-" + comprobanteDesc.getVenta().getNumeroComprobante() + " Status: " + comprobanteDesc.getMensajeRespuestaSunat());

        auditService.registrarLog("Admin", "REGISTRO",
                "VENTAS", "Venta registrada - Total: " + venta.getTotal(), "127.0.0.1");

        return mapper.toDTO(saved);
    }
}
