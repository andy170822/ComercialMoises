package com.comercialmoises.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "Compra")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCompra;

    @ManyToOne
    @JoinColumn(name = "IdProveedor", nullable = false)
    private Proveedor proveedor;

    @ManyToOne
    @JoinColumn(name = "IdUsuario", nullable = false)
    private Usuario usuario;

    private LocalDateTime fechaCompra = LocalDateTime.now();

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
    private String estado = "REGISTRADA";
}
