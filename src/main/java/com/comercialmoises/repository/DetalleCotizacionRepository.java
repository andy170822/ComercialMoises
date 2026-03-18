package com.comercialmoises.repository;

import com.comercialmoises.model.DetalleCotizacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleCotizacionRepository extends JpaRepository<DetalleCotizacion, Long> {
}
