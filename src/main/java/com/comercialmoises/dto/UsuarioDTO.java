package com.comercialmoises.dto;

import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {
    private Integer idUsuario;
    private Integer idRol;
    private String nombreRol;
    private String nombres;
    private String apellidos;
    private String dni;
    private String correo;
    private String username;
    private String password; // Used for input, not exposed in output ideally
    private Boolean estado;
    private LocalDateTime fechaRegistro;
}
