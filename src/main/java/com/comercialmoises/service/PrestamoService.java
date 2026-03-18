package com.comercialmoises.service;

import com.comercialmoises.model.Prestamo;
import com.comercialmoises.repository.PrestamoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrestamoService {

    @Autowired
    private PrestamoRepository prestamoRepository;

    public List<Prestamo> listarTodos() {
        return prestamoRepository.findAll();
    }

    public Prestamo guardar(Prestamo prestamo) {
        if (prestamo == null)
            throw new IllegalArgumentException("Prestamo cannot be null");
        return prestamoRepository.save(prestamo);
    }

    public Prestamo obtenerPorId(Long id) {
        if (id == null)
            return null;
        return prestamoRepository.findById(id).orElse(null);
    }

    public void eliminar(Long id) {
        if (id == null)
            return;
        prestamoRepository.deleteById(id);
    }
}
