package com.comercialmoises.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Cliente")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCliente;

    @Column(length = 20)
    private String tipoDocumento;

    @Column(unique = true, length = 20)
    private String numeroDocumento;

    @Column(length = 100)
    private String nombres;

    @Column(length = 100)
    private String apellidos;

    @Column(length = 150)
    private String razonSocial;

    @Column(length = 20)
    private String telefono;

    @Column(length = 100)
    private String email;

    @Column(length = 200)
    private String direccion;

    private Boolean estado = true;
}
