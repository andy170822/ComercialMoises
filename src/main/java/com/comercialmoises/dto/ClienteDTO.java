package com.comercialmoises.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDTO {
    private Integer idCliente;
    private String tipoDocumento;
    private String numeroDocumento;
    private String nombres;
    private String apellidos;
    private String razonSocial;
    private String telefono;
    private String email;
    private String direccion;
    private Boolean estado;
}
