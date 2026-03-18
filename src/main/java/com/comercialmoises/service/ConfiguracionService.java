package com.comercialmoises.service;

import com.comercialmoises.model.Configuracion;
import com.comercialmoises.repository.ConfiguracionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConfiguracionService {

    @Autowired
    private ConfiguracionRepository configuracionRepository;

    public Configuracion obtenerConfiguracionActual() {
        List<Configuracion> configs = configuracionRepository.findAll();
        if (configs.isEmpty()) {
            Configuracion defaultConfig = new Configuracion();
            defaultConfig.setEmpresaNombre("Ferretería Moisés");
            defaultConfig.setRuc("20445566778");
            defaultConfig.setMoneda("Soles (S/)");
            defaultConfig.setTemaInterfaz("ketzup");
            return configuracionRepository.save(defaultConfig);
        }
        return configs.get(0);
    }

    public Configuracion guardar(Configuracion configuracion) {
        if (configuracion == null)
            throw new IllegalArgumentException("Configuracion cannot be null");
        return configuracionRepository.save(configuracion);
    }
}
