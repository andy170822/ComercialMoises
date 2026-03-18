package com.comercialmoises.services;

import com.comercialmoises.dto.CajaDTO;
import java.math.BigDecimal;

public interface CajaService {
    CajaDTO obtenerCajaActual();

    CajaDTO abrirCaja(CajaDTO dto);

    CajaDTO cerrarCaja(Integer idCaja, BigDecimal montoReal);

    CajaDTO registrarMovimiento(Integer idCaja, String tipo, BigDecimal monto);
}
