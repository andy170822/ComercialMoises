package com.comercialmoises.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class PrestamoDTO {
    private Long idPrestamo;
    private String cliente; // nombre del cliente aplanado
    private BigDecimal monto; // montoOriginal
    private BigDecimal saldoPendiente; // saldo restante
    private String tasaInteres; // "5%", "10%", etc.
    private String estado; // VIGENTE, PAGADO, EN_MORA
    private Long idCliente; // para crear prestamo referenciando a un cliente real
}
