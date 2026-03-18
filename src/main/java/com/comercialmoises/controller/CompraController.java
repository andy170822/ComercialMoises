package com.comercialmoises.controller;

import com.comercialmoises.dto.CompraDTO;
import com.comercialmoises.services.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/compras")
@CrossOrigin(origins = "*")
public class CompraController {

    @Autowired
    private CompraService service;

    @GetMapping
    public ResponseEntity<List<CompraDTO>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompraDTO> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<CompraDTO> guardar(@RequestBody CompraDTO dto) {
        return new ResponseEntity<>(service.guardar(dto), HttpStatus.CREATED);
    }
}
