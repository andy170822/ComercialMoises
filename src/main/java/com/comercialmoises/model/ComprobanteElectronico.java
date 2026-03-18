package com.comercialmoises.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "comprobante_electronico")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ComprobanteElectronico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_venta", referencedColumnName = "idVenta", nullable = false)
    private Venta venta;

    @Column(columnDefinition = "TEXT")
    private String xmlGenerado;

    @Column(columnDefinition = "TEXT")
    private String hashFirma;

    private String codigoRespuestaSunat;

    @Column(columnDefinition = "TEXT")
    private String mensajeRespuestaSunat;

    private LocalDateTime fechaEnvio = LocalDateTime.now();
    
    // Status to track if it was accepted by SUNAT
    private Boolean aceptado = false;
}
