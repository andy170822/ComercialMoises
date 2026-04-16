package com.comercialmoises.controller;

import com.comercialmoises.dto.CotizacionDTO;
import com.comercialmoises.model.Cotizacion;
import com.comercialmoises.service.CotizacionService;
import com.comercialmoises.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/cotizaciones")
@CrossOrigin(origins = "*")
public class CotizacionController {

    @Autowired
    private CotizacionService cotizacionService;

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping
    public List<CotizacionDTO> getAllCotizaciones() {
        return cotizacionService.listarTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CotizacionDTO> getById(@PathVariable Long id) {
        CotizacionDTO dto = cotizacionService.obtenerPorId(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public Cotizacion createCotizacion(@RequestBody Cotizacion cotizacion) {
        // Vinculación bidireccional para los detalles
        if (cotizacion.getDetalles() != null) {
            cotizacion.getDetalles().forEach(d -> d.setCotizacion(cotizacion));
        }
        
        // Generar valores predeterminados si faltan
        if (cotizacion.getFechaEmision() == null) {
            cotizacion.setFechaEmision(LocalDateTime.now());
        }
        
        if (cotizacion.getNumeroCotizacion() == null || cotizacion.getNumeroCotizacion().isEmpty()) {
            cotizacion.setNumeroCotizacion("COT-" + System.currentTimeMillis() / 1000);
        }

        if (cotizacion.getEstado() == null) {
            cotizacion.setEstado("PENDIENTE");
        }
        
        return cotizacionService.guardar(cotizacion);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCotizacion(@PathVariable Long id) {
        cotizacionService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
