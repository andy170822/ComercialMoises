package com.comercialmoises.dto;

import lombok.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoDTO {
    private Integer idProducto;
    private Integer idCategoria;
    private String nombreCategoria;
    private String codigo;
    private String nombreProducto;
    private String descripcion;
    private BigDecimal precioCompra;
    private BigDecimal precioVenta;
    private Integer stockActual;
    private Integer stockMinimo;
    private String unidadMedida;
    private Boolean estado;
}
