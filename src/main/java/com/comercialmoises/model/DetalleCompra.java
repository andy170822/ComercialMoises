package com.comercialmoises.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(name = "DetalleCompra")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DetalleCompra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDetalleCompra;

    @ManyToOne
    @JoinColumn(name = "IdCompra", nullable = false)
    private Compra compra;

    @ManyToOne
    @JoinColumn(name = "IdProducto", nullable = false)
    private Producto producto;

    @Column(nullable = false)
    private Integer cantidad;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal precioUnitario;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal importe;
}
