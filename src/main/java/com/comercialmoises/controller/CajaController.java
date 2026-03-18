package com.comercialmoises.controller;

import com.comercialmoises.dto.CajaDTO;
import com.comercialmoises.services.CajaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping("/api/caja")
@CrossOrigin(origins = "*")
public class CajaController {

    @Autowired
    private CajaService cajaService;

    @GetMapping("/actual")
    public ResponseEntity<CajaDTO> obtenerCajaActual() {
        CajaDTO caja = cajaService.obtenerCajaActual();
        if (caja == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(caja);
    }

    @PostMapping("/abrir")
    public ResponseEntity<CajaDTO> abrirCaja(@RequestBody CajaDTO dto) {
        return ResponseEntity.ok(cajaService.abrirCaja(dto));
    }

    @PostMapping("/{id}/cerrar")
    public ResponseEntity<CajaDTO> cerrarCaja(@PathVariable Integer id, @RequestBody Map<String, BigDecimal> payload) {
        BigDecimal montoReal = payload.get("montoReal");
        return ResponseEntity.ok(cajaService.cerrarCaja(id, montoReal));
    }

    @PostMapping("/{id}/movimiento")
    public ResponseEntity<CajaDTO> registrarMovimiento(@PathVariable Integer id,
            @RequestBody Map<String, Object> payload) {
        String tipo = (String) payload.get("tipo");
        BigDecimal monto = new BigDecimal(payload.get("monto").toString());
        return ResponseEntity.ok(cajaService.registrarMovimiento(id, tipo, monto));
    }
}
