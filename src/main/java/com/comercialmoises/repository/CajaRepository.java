package com.comercialmoises.repository;

import com.comercialmoises.model.Caja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CajaRepository extends JpaRepository<Caja, Integer> {
    Optional<Caja> findTopByEstadoOrderByFechaAperturaDesc(String estado);
}
