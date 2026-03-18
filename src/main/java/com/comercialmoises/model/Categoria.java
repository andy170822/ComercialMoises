package com.comercialmoises.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Categoria")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCategoria;

    @Column(nullable = false, length = 100)
    private String nombreCategoria;

    @Column(length = 200)
    private String descripcion;

    private Boolean estado = true;
}
