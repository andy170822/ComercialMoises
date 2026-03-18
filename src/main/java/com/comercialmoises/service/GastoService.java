package com.comercialmoises.service;

import com.comercialmoises.model.Gasto;
import com.comercialmoises.repository.GastoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GastoService {

    @Autowired
    private GastoRepository gastoRepository;

    @Autowired
    private AuditoriaLogService auditService;

    public List<Gasto> listarTodos() {
        return gastoRepository.findAll();
    }

    public Gasto guardar(Gasto gasto) {
        Gasto saved = gastoRepository.save(gasto);
        auditService.registrarLog("Admin", "REGISTRO", "GASTOS",
                "Registro de gasto: " + saved.getConcepto() + " - S/ " + saved.getMonto(), "127.0.0.1");
        return saved;
    }

    public Gasto obtenerPorId(Long id) {
        return gastoRepository.findById(id).orElse(null);
    }

    public void eliminar(Long id) {
        gastoRepository.deleteById(id);
    }
}
