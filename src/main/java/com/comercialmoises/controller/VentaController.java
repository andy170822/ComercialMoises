package com.comercialmoises.controller;

import com.comercialmoises.dto.VentaDTO;
import com.comercialmoises.services.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/ventas")
@CrossOrigin(origins = "*")
public class VentaController {

    @Autowired
    private VentaService service;

    @GetMapping
    public ResponseEntity<List<VentaDTO>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VentaDTO> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<VentaDTO> guardar(@RequestBody VentaDTO dto) {
        return new ResponseEntity<>(service.guardar(dto), HttpStatus.CREATED);
    }
}
