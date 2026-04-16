package com.comercialmoises.service;

import com.comercialmoises.dto.CotizacionDTO;
import com.comercialmoises.mappers.CotizacionMapper;
import com.comercialmoises.model.Cotizacion;
import com.comercialmoises.repository.CotizacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CotizacionService {

    @Autowired
    private CotizacionRepository cotizacionRepository;

    @Autowired
    private CotizacionMapper mapper;

    public List<CotizacionDTO> listarTodas() {
        return cotizacionRepository.findByEstadoNot("ELIMINADA").stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    public Cotizacion guardar(Cotizacion cotizacion) {
        if (cotizacion == null)
            throw new IllegalArgumentException("Cotizacion cannot be null");
        return cotizacionRepository.save(cotizacion);
    }

    public CotizacionDTO obtenerPorId(Long id) {
        if (id == null)
            return null;
        return cotizacionRepository.findById(id).map(mapper::toDTO).orElse(null);
    }

    public void eliminar(Long id) {
        if (id == null)
            return;
        cotizacionRepository.findById(id).ifPresent(c -> {
            c.setEstado("ELIMINADA");
            cotizacionRepository.save(c);
        });
    }
}
