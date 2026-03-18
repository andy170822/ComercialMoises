package com.comercialmoises.controller;

import com.comercialmoises.model.Cotizacion;
import com.comercialmoises.service.CotizacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cotizaciones")
@CrossOrigin(origins = "*")
public class CotizacionController {

    @Autowired
    private CotizacionService cotizacionService;

    @GetMapping
    public List<Cotizacion> getAllCotizaciones() {
        return cotizacionService.listarTodas();
    }

    @PostMapping
    public Cotizacion createCotizacion(@RequestBody Cotizacion cotizacion) {
        if (cotizacion.getDetalles() != null) {
            cotizacion.getDetalles().forEach(d -> d.setCotizacion(cotizacion));
        }
        return cotizacionService.guardar(cotizacion);
    }
}
