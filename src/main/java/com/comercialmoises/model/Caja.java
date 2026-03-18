package com.comercialmoises.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "caja")
@Data
public class Caja {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCaja;

    private LocalDateTime fechaApertura;
    private LocalDateTime fechaCierre;

    @Column(precision = 10, scale = 2)
    private BigDecimal montoInicial;

    @Column(precision = 10, scale = 2)
    private BigDecimal ingresosVentas;

    @Column(precision = 10, scale = 2)
    private BigDecimal egresosGastos;

    @Column(precision = 10, scale = 2)
    private BigDecimal ingresosAdicionales;

    @Column(precision = 10, scale = 2)
    private BigDecimal egresosAdicionales;

    @Column(precision = 10, scale = 2)
    private BigDecimal montoFinalEstimado;

    @Column(precision = 10, scale = 2)
    private BigDecimal montoFinalReal;

    @Column(length = 20)
    private String estado; // ABIERTA, CERRADA

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuarioApertura;
}
