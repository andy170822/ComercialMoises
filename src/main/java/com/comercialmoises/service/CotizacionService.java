package com.comercialmoises.service;

import com.comercialmoises.model.Cotizacion;
import com.comercialmoises.repository.CotizacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CotizacionService {

    @Autowired
    private CotizacionRepository cotizacionRepository;

    public List<Cotizacion> listarTodas() {
        return cotizacionRepository.findAll();
    }

    public Cotizacion guardar(Cotizacion cotizacion) {
        if (cotizacion == null)
            throw new IllegalArgumentException("Cotizacion cannot be null");
        return cotizacionRepository.save(cotizacion);
    }

    public Cotizacion obtenerPorId(Long id) {
        if (id == null)
            return null;
        return cotizacionRepository.findById(id).orElse(null);
    }

    public void eliminar(Long id) {
        if (id == null)
            return;
        cotizacionRepository.deleteById(id);
    }
}
