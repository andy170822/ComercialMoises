package com.comercialmoises.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(name = "DetalleVenta")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DetalleVenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDetalleVenta;

    @ManyToOne
    @JoinColumn(name = "IdVenta", nullable = false)
    private Venta venta;

    @ManyToOne
    @JoinColumn(name = "IdProducto", nullable = false)
    private Producto producto;

    @Column(nullable = false)
    private Integer cantidad;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal precioUnitario;

    @Column(precision = 10, scale = 2)
    private BigDecimal descuento = BigDecimal.ZERO;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal importe;
}
