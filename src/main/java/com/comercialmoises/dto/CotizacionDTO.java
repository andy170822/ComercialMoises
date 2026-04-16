package com.comercialmoises.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CotizacionDTO {
    private Long idCotizacion;
    private String numeroCotizacion;
    private Integer idCliente;
    private String nombreCliente;
    private Integer idUsuario;
    private LocalDateTime fechaEmision;
    private LocalDateTime fechaVencimiento;
    private BigDecimal total;
    private String estado;
    private List<DetalleCotizacionDTO> detalles;
}
