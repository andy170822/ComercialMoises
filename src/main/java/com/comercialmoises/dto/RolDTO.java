package com.comercialmoises.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RolDTO {
    private Integer idRol;
    private String nombreRol;
    private String descripcion;
    private Boolean estado;
}
