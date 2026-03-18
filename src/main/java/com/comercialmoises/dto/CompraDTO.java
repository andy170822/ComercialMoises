package com.comercialmoises.dto;

import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompraDTO {
    private Integer idCompra;
    private Integer idProveedor;
    private String razonSocialProveedor;
    private Integer idUsuario;
    private String nombreUsuario;
    private LocalDateTime fechaCompra;
    private String tipoComprobante;
    private String serie;
    private String numeroComprobante;
    private BigDecimal subTotal;
    private BigDecimal igv;
    private BigDecimal total;
    private String estado;
    private List<DetalleCompraDTO> detalles;
}
