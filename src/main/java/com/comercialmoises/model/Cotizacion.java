package com.comercialmoises.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "cotizaciones")
@Data
@NoArgsConstructor
public class Cotizacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCotizacion;

    private String numeroCotizacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario")
    private Usuario usuario; // Vendedor

    private LocalDateTime fechaEmision;
    private LocalDateTime fechaVencimiento;

    @Column(precision = 10, scale = 2)
    private BigDecimal total;

    private String estado; // EMITIDA, APROBADA, RECHAZADA, VENCIDA

    @OneToMany(mappedBy = "cotizacion", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleCotizacion> detalles;
}
