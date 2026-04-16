package com.comercialmoises.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VentaHistorialDTO {
    private Integer idVenta;
    private LocalDateTime fechaVenta;
    private String comprobante; // "serie-numero"
    private BigDecimal total;
    private String estado;
}
