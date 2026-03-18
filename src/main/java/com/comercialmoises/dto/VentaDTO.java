package com.comercialmoises.dto;

import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VentaDTO {
    private Integer idVenta;
    private Integer idCliente;
    private String nombreCliente;
    private Integer idUsuario;
    private String nombreUsuario;
    private LocalDateTime fechaVenta;
    private String tipoComprobante;
    private String serie;
    private String numeroComprobante;
    private BigDecimal subTotal;
    private BigDecimal igv;
    private BigDecimal total;
    private String estado;
    private List<DetalleVentaDTO> detalles;
}
