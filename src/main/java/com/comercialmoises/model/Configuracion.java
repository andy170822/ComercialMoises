package com.comercialmoises.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "configuracion")
@Data
@NoArgsConstructor
public class Configuracion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idConfiguracion;

    private String empresaNombre;
    private String ruc;
    private String direccionFiscal;
    private String moneda;

    // UI Settings
    private String temaInterfaz; // oscuro, ketzup, ecologico
}
