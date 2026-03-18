package com.comercialmoises.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Proveedor")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Proveedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProveedor;

    @Column(nullable = false, length = 150)
    private String razonSocial;

    @Column(unique = true, length = 11)
    private String ruc;

    @Column(length = 20)
    private String telefono;

    @Column(length = 100)
    private String email;

    @Column(length = 200)
    private String direccion;

    private Boolean estado = true;
}
