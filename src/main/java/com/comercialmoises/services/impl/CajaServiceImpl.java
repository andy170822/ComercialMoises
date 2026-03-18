package com.comercialmoises.services.impl;

import com.comercialmoises.dto.CajaDTO;
import com.comercialmoises.exception.ResourceNotFoundException;
import com.comercialmoises.model.Caja;
import com.comercialmoises.model.Usuario;
import com.comercialmoises.repository.CajaRepository;
import com.comercialmoises.services.CajaService;
import com.comercialmoises.service.AuditoriaLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CajaServiceImpl implements CajaService {

    @Autowired
    private CajaRepository cajaRepository;

    @Autowired
    private AuditoriaLogService auditService;

    private CajaDTO convertToDTO(Caja caja) {
        if (caja == null)
            return null;
        CajaDTO dto = new CajaDTO();
        dto.setIdCaja(caja.getIdCaja());
        dto.setFechaApertura(caja.getFechaApertura());
        dto.setFechaCierre(caja.getFechaCierre());
        dto.setMontoInicial(caja.getMontoInicial());
        dto.setIngresosVentas(caja.getIngresosVentas());
        dto.setEgresosGastos(caja.getEgresosGastos());
        dto.setIngresosAdicionales(caja.getIngresosAdicionales());
        dto.setEgresosAdicionales(caja.getEgresosAdicionales());
        dto.setMontoFinalEstimado(caja.getMontoFinalEstimado());
        dto.setMontoFinalReal(caja.getMontoFinalReal());
        dto.setEstado(caja.getEstado());
        if (caja.getUsuarioApertura() != null) {
            dto.setIdUsuarioApertura(caja.getUsuarioApertura().getIdUsuario());
        }
        return dto;
    }

    @Override
    public CajaDTO obtenerCajaActual() {
        Optional<Caja> opt = cajaRepository.findTopByEstadoOrderByFechaAperturaDesc("ABIERTA");
        return opt.map(this::convertToDTO).orElse(null);
    }

    @Override
    public CajaDTO abrirCaja(CajaDTO dto) {
        // Validar que no haya una caja abierta
        if (obtenerCajaActual() != null) {
            throw new RuntimeException("Ya existe una caja abierta");
        }

        Caja caja = new Caja();
        caja.setFechaApertura(LocalDateTime.now());
        caja.setMontoInicial(dto.getMontoInicial() != null ? dto.getMontoInicial() : BigDecimal.ZERO);
        caja.setIngresosVentas(BigDecimal.ZERO);
        caja.setEgresosGastos(BigDecimal.ZERO);
        caja.setIngresosAdicionales(BigDecimal.ZERO);
        caja.setEgresosAdicionales(BigDecimal.ZERO);
        caja.setMontoFinalEstimado(caja.getMontoInicial());
        caja.setEstado("ABIERTA");

        if (dto.getIdUsuarioApertura() != null) {
            Usuario u = new Usuario();
            u.setIdUsuario(dto.getIdUsuarioApertura());
            caja.setUsuarioApertura(u);
        }

        Caja saved = cajaRepository.save(caja);
        auditService.registrarLog("Usuario", "APERTURA", "CAJA", "Apertura de caja con S/ " + saved.getMontoInicial(),
                "127.0.0.1");

        return convertToDTO(saved);
    }

    @Override
    public CajaDTO cerrarCaja(Integer idCaja, BigDecimal montoReal) {
        if (idCaja == null)
            throw new IllegalArgumentException("ID cannot be null");
        Caja caja = cajaRepository.findById(idCaja)
                .orElseThrow(() -> new ResourceNotFoundException("Caja no encontrada"));

        if (!"ABIERTA".equals(caja.getEstado())) {
            throw new RuntimeException("La caja ya está cerrada");
        }

        caja.setFechaCierre(LocalDateTime.now());
        caja.setMontoFinalReal(montoReal);
        caja.setEstado("CERRADA");

        Caja saved = cajaRepository.save(caja);
        auditService.registrarLog("Admin", "CIERRE", "CAJA", "Cierre de caja - Saldo Real: S/ " + montoReal,
                "127.0.0.1");

        return convertToDTO(saved);
    }

    @Override
    public CajaDTO registrarMovimiento(Integer idCaja, String tipo, BigDecimal monto) {
        if (idCaja == null)
            throw new IllegalArgumentException("ID cannot be null");
        Caja caja = cajaRepository.findById(idCaja)
                .orElseThrow(() -> new ResourceNotFoundException("Caja no encontrada"));

        if (!"ABIERTA".equals(caja.getEstado())) {
            throw new RuntimeException("La caja no está abierta");
        }

        if ("INGRESO".equals(tipo)) {
            caja.setIngresosAdicionales(caja.getIngresosAdicionales().add(monto));
        } else if ("EGRESO".equals(tipo)) {
            caja.setEgresosAdicionales(caja.getEgresosAdicionales().add(monto));
        } else if ("VENTA".equals(tipo)) {
            caja.setIngresosVentas(caja.getIngresosVentas().add(monto));
        }

        // Recalcular estimado
        BigDecimal estimado = caja.getMontoInicial()
                .add(caja.getIngresosVentas())
                .add(caja.getIngresosAdicionales())
                .subtract(caja.getEgresosGastos())
                .subtract(caja.getEgresosAdicionales());

        caja.setMontoFinalEstimado(estimado);

        return convertToDTO(cajaRepository.save(caja));
    }
}
