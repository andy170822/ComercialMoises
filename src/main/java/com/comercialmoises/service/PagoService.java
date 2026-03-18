package com.comercialmoises.service;

import com.comercialmoises.model.Pago;
import com.comercialmoises.repository.PagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PagoService {

    @Autowired
    private PagoRepository pagoRepository;

    public List<Pago> listarTodos() {
        return pagoRepository.findAll();
    }

    public Pago guardar(Pago pago) {
        return pagoRepository.save(pago);
    }

    public Pago obtenerPorId(Integer id) {
        if (id == null)
            return null;
        return pagoRepository.findById(id).orElse(null);
    }

    public void eliminar(Integer id) {
        if (id == null)
            return;
        pagoRepository.deleteById(id);
    }
}
