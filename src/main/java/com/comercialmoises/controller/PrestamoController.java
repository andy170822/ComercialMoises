package com.comercialmoises.controller;

import com.comercialmoises.dto.PrestamoDTO;
import com.comercialmoises.model.Cliente;
import com.comercialmoises.model.Prestamo;
import com.comercialmoises.service.PrestamoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/prestamos")
@CrossOrigin(origins = "*")
public class PrestamoController {

    @Autowired
    private PrestamoService prestamoService;

    private PrestamoDTO toDTO(Prestamo p) {
        PrestamoDTO dto = new PrestamoDTO();
        dto.setIdPrestamo(p.getIdPrestamo());
        if (p.getCliente() != null) {
            dto.setCliente(p.getCliente().getNombres() + " " + p.getCliente().getApellidos());
            dto.setIdCliente(Long.valueOf(p.getCliente().getIdCliente()));
        } else {
            dto.setCliente("Sin cliente");
        }
        dto.setMonto(p.getMontoOriginal());
        dto.setSaldoPendiente(p.getSaldo());
        dto.setTasaInteres(p.getInteresPorcentaje() != null
                ? p.getInteresPorcentaje().toPlainString() + "%"
                : "0%");
        dto.setEstado(p.getEstado());
        return dto;
    }

    @GetMapping
    public ResponseEntity<List<PrestamoDTO>> listarPrestamos() {
        List<PrestamoDTO> list = prestamoService.listarTodos()
                .stream().map(this::toDTO).collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    @PostMapping
    public ResponseEntity<PrestamoDTO> crearPrestamo(@RequestBody PrestamoDTO dto) {
        Prestamo prestamo = new Prestamo();

        if (dto.getIdCliente() != null) {
            Cliente cliente = new Cliente();
            cliente.setIdCliente(dto.getIdCliente().intValue());
            prestamo.setCliente(cliente);
        }

        prestamo.setMontoOriginal(dto.getMonto() != null ? dto.getMonto() : BigDecimal.ZERO);
        prestamo.setSaldo(dto.getMonto() != null ? dto.getMonto() : BigDecimal.ZERO);

        // Parse interest: "5%" → 5
        if (dto.getTasaInteres() != null) {
            String raw = dto.getTasaInteres().replace("%", "").trim();
            try {
                prestamo.setInteresPorcentaje(new BigDecimal(raw));
            } catch (NumberFormatException e) {
                prestamo.setInteresPorcentaje(BigDecimal.ZERO);
            }
        }

        prestamo.setEstado(dto.getEstado() != null ? dto.getEstado() : "VIGENTE");
        prestamo.setFechaOtorgado(LocalDateTime.now());

        Prestamo saved = prestamoService.guardar(prestamo);
        return new ResponseEntity<>(toDTO(saved), HttpStatus.CREATED);
    }

    @PutMapping("/{id}/pago-parcial")
    public ResponseEntity<PrestamoDTO> registrarPago(@PathVariable Long id,
            @RequestBody java.util.Map<String, Object> body) {
        Prestamo p = prestamoService.obtenerPorId(id);
        if (p == null)
            return ResponseEntity.notFound().build();

        BigDecimal pago = new BigDecimal(body.get("monto").toString());
        BigDecimal nuevoSaldo = p.getSaldo().subtract(pago);
        p.setSaldo(nuevoSaldo.compareTo(BigDecimal.ZERO) < 0 ? BigDecimal.ZERO : nuevoSaldo);
        if (p.getSaldo().compareTo(BigDecimal.ZERO) == 0) {
            p.setEstado("PAGADO");
        }

        return ResponseEntity.ok(toDTO(prestamoService.guardar(p)));
    }
}
