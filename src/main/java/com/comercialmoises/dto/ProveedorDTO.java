package com.comercialmoises.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProveedorDTO {
    private Integer idProveedor;
    private String razonSocial;
    private String ruc;
    private String telefono;
    private String email;
    private String direccion;
    private Boolean estado;
}
