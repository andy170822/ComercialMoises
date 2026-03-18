package com.comercialmoises.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(name = "Producto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProducto;

    @ManyToOne
    @JoinColumn(name = "IdCategoria", nullable = false)
    private Categoria categoria;

    @Column(unique = true, nullable = false, length = 50)
    private String codigo;

    @Column(nullable = false, length = 150)
    private String nombreProducto;

    @Column(length = 250)
    private String descripcion;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal precioCompra;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal precioVenta;

    @Column(nullable = false)
    private Integer stockActual = 0;

    private Integer stockMinimo = 5;

    @Column(length = 20)
    private String unidadMedida;

    private Boolean estado = true;
}
