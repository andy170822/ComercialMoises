package com.comercialmoises.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "gastos")
@Data
@NoArgsConstructor
public class Gasto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idGasto;

    @Column(length = 255)
    private String concepto;

    @Column(precision = 10, scale = 2)
    private BigDecimal monto;

    private LocalDateTime fecha;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    private String estado; // EJ: PAGADO, PENDIENTE, ANULADO
}
