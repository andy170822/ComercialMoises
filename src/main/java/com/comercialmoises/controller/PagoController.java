package com.comercialmoises.controller;

import com.comercialmoises.model.Pago;
import com.comercialmoises.service.PagoService;
import com.comercialmoises.service.AuditoriaLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/pagos")
@CrossOrigin(origins = "*")
public class PagoController {

    @Autowired
    private PagoService pagoService;

    @Autowired
    private AuditoriaLogService auditService;

    @GetMapping
    public ResponseEntity<List<Pago>> listarPagos() {
        return ResponseEntity.ok(pagoService.listarTodos());
    }

    @PostMapping
    public ResponseEntity<Pago> registrarPago(@RequestBody Pago pago) {
        if (pago.getFechaPago() == null) {
            pago.setFechaPago(LocalDateTime.now());
        }
        if (pago.getEstado() == null) {
            pago.setEstado("APROBADO");
        }
        Pago saved = pagoService.guardar(pago);
        auditService.registrarLog("Admin", "REGISTRO", "PAGOS",
                "Pago registrado - Cliente: " + saved.getCliente() + " - S/ " + saved.getMonto(), "127.0.0.1");
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<Pago> actualizarEstado(@PathVariable Integer id,
            @RequestBody java.util.Map<String, String> body) {
        Pago pago = pagoService.obtenerPorId(id);
        if (pago == null)
            return ResponseEntity.notFound().build();
        pago.setEstado(body.get("estado"));
        return ResponseEntity.ok(pagoService.guardar(pago));
    }
}
