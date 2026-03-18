package com.comercialmoises.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "Venta")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idVenta;

    @ManyToOne
    @JoinColumn(name = "IdCliente", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "IdUsuario", nullable = false)
    private Usuario usuario;

    private LocalDateTime fechaVenta = LocalDateTime.now();

    @Column(length = 50)
    private String tipoComprobante;

    @Column(length = 10)
    private String serie;

    @Column(length = 20)
    private String numeroComprobante;

    @Column(precision = 12, scale = 2)
    private BigDecimal subTotal;

    @Column(precision = 12, scale = 2)
    private BigDecimal igv;

    @Column(precision = 12, scale = 2)
    private BigDecimal total;

    @Column(length = 20)
    private String estado = "EMITIDA";
}
