package com.comercialmoises.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "MovimientoInventario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovimientoInventario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMovimiento;

    @ManyToOne
    @JoinColumn(name = "IdProducto", nullable = false)
    private Producto producto;

    @Column(nullable = false, length = 20)
    private String tipoMovimiento;

    @Column(nullable = false)
    private Integer cantidad;

    private LocalDateTime fechaMovimiento = LocalDateTime.now();

    @Column(length = 200)
    private String observacion;
}
