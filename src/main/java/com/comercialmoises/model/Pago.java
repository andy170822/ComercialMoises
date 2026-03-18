package com.comercialmoises.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "Pago")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPago;

    @ManyToOne
    @JoinColumn(name = "IdVenta", nullable = true)
    private Venta venta;

    private LocalDateTime fechaPago = LocalDateTime.now();

    @Column(length = 50)
    private String metodoPago;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal monto;

    // Standalone payment fields
    @Column(length = 200)
    private String cliente;

    @Column(length = 30)
    private String estado; // APROBADO, PENDIENTE, ANULADO

    @Column(length = 255)
    private String descripcion;
}
