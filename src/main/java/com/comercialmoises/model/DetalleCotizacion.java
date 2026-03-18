package com.comercialmoises.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Entity
@Table(name = "detalle_cotizacion")
@Data
@NoArgsConstructor
public class DetalleCotizacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDetalleCotizacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cotizacion")
    private Cotizacion cotizacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_producto")
    private Producto producto;

    private Integer cantidad;

    @Column(precision = 10, scale = 2)
    private BigDecimal precioUnitario;

    @Column(precision = 10, scale = 2)
    private BigDecimal subtotal;
}
