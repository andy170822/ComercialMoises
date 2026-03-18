package com.comercialmoises.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Auditoria")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Auditoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAuditoria;

    @Column(length = 100)
    private String tablaAfectada;

    @Column(length = 20)
    private String tipoOperacion;

    @Column(length = 100)
    private String usuarioResponsable;

    private LocalDateTime fechaOperacion = LocalDateTime.now();

    @Column(length = 300)
    private String descripcion;
}
