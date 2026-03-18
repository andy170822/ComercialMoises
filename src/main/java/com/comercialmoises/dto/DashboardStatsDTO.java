package com.comercialmoises.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class DashboardStatsDTO {
    private BigDecimal ventasDelMes;
    private BigDecimal cajaActual;
    private Integer clientesActivos;
    private Integer stockBajo;
}
