package com.comercialmoises.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdUsuario")
    private Integer idUsuario;

    @ManyToOne
    @JoinColumn(name = "IdRol", nullable = false)
    private Rol rol;

    @Column(name = "Nombres", nullable = false, length = 100)
    private String nombres;

    @Column(name = "Apellidos", nullable = false, length = 100)
    private String apellidos;

    @Column(name = "DNI", unique = true, length = 8)
    private String dni;

    @Column(name = "Correo", unique = true, length = 100)
    private String correo;

    @Column(name = "Username", nullable = false, unique = true, length = 50)
    private String username;

    @Column(name = "PasswordHash", nullable = false, length = 255)
    private String passwordHash;

    @Column(name = "Estado", nullable = false)
    private Boolean estado = true;

    @Column(name = "FechaRegistro")
    private LocalDateTime fechaRegistro = LocalDateTime.now();
}
