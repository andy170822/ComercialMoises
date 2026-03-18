package com.comercialmoises.service;

import com.comercialmoises.model.AuditoriaLog;
import com.comercialmoises.repository.AuditoriaLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AuditoriaLogService {

    @Autowired
    private AuditoriaLogRepository auditoriaLogRepository;

    public List<AuditoriaLog> listarTodos() {
        return auditoriaLogRepository.findAll();
    }

    public AuditoriaLog registrarLog(String usuario, String accion, String modulo, String detalle, String ip) {
        AuditoriaLog log = new AuditoriaLog();
        log.setUsuario(usuario);
        log.setAccion(accion);
        log.setModulo(modulo);
        log.setDetalle(detalle);
        log.setIp(ip);
        log.setFecha(LocalDateTime.now());
        return auditoriaLogRepository.save(log);
    }
}
