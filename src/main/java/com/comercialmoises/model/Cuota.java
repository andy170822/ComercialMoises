package com.comercialmoises.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "cuotas")
@Data
@NoArgsConstructor
public class Cuota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCuota;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_prestamo")
    private Prestamo prestamo;

    private Integer numeroCuota;

    @Column(precision = 10, scale = 2)
    private BigDecimal monto;

    private LocalDateTime fechaVencimiento;
    private LocalDateTime fechaPago;

    private String estado; // PENDIENTE, PAGADO, MORA
}
