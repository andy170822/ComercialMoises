package com.comercialmoises.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "auditoria_logs")
@Data
@NoArgsConstructor
public class AuditoriaLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLog;

    private String usuario; // username
    private String accion;
    private String modulo;
    private String detalle;
    private String ip;
    private LocalDateTime fecha;
}
