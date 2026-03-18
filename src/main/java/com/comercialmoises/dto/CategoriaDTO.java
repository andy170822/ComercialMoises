package com.comercialmoises.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaDTO {
    private Integer idCategoria;
    private String nombreCategoria;
    private String descripcion;
    private Boolean estado;
}
