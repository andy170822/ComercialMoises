package com.comercialmoises.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Rol")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdRol")
    private Integer idRol;

    @Column(name = "NombreRol", nullable = false, length = 50)
    private String nombreRol;

    @Column(name = "Descripcion", length = 255)
    private String descripcion;

    @Column(name = "Estado", nullable = false)
    private Boolean estado = true;
}
