package com.comercialmoises.controller;

import com.comercialmoises.model.Configuracion;
import com.comercialmoises.service.ConfiguracionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/configuracion")
@CrossOrigin(origins = "*")
public class ConfiguracionController {

    @Autowired
    private ConfiguracionService configuracionService;

    @GetMapping
    public Configuracion getConfiguracion() {
        return configuracionService.obtenerConfiguracionActual();
    }

    @PutMapping
    public Configuracion updateConfiguracion(@RequestBody Configuracion configuracion) {
        Configuracion current = configuracionService.obtenerConfiguracionActual();
        current.setEmpresaNombre(configuracion.getEmpresaNombre());
        current.setRuc(configuracion.getRuc());
        current.setDireccionFiscal(configuracion.getDireccionFiscal());
        current.setMoneda(configuracion.getMoneda());
        return configuracionService.guardar(current);
    }
}
