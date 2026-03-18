package com.comercialmoises.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductoMasVendidoDTO {
    private String nombre;
    private Long cantidadVendida;
}
