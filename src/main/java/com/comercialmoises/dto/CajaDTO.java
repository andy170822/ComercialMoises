package com.comercialmoises.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class CajaDTO {
    private Integer idCaja;
    private LocalDateTime fechaApertura;
    private LocalDateTime fechaCierre;
    private BigDecimal montoInicial;
    private BigDecimal ingresosVentas;
    private BigDecimal egresosGastos;
    private BigDecimal ingresosAdicionales;
    private BigDecimal egresosAdicionales;
    private BigDecimal montoFinalEstimado;
    private BigDecimal montoFinalReal;
    private String estado;
    private Integer idUsuarioApertura;
}
