package com.comercialmoises.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "prestamos")
@Data
@NoArgsConstructor
public class Prestamo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPrestamo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario")
    private Usuario usuario; // Quien lo dio

    @Column(precision = 10, scale = 2)
    private BigDecimal montoOriginal;

    @Column(precision = 10, scale = 2)
    private BigDecimal saldo;

    @Column(precision = 5, scale = 2)
    private BigDecimal interesPorcentaje;

    private LocalDateTime fechaOtorgado;

    private String estado; // VIGENTE, PAGADO, EN_MORA

    @OneToMany(mappedBy = "prestamo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cuota> cuotas;
}
