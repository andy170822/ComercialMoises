package com.comercialmoises.controller;

import com.comercialmoises.model.Gasto;
import com.comercialmoises.service.GastoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/gastos")
@CrossOrigin(origins = "*")
public class GastoController {

    @Autowired
    private GastoService gastoService;

    @GetMapping
    public List<Gasto> listarGastos() {
        return gastoService.listarTodos();
    }

    @PostMapping
    public Gasto registrarGasto(@RequestBody Gasto gasto) {
        return gastoService.guardar(gasto);
    }
}
