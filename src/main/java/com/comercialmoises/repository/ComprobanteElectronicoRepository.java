package com.comercialmoises.repository;

import com.comercialmoises.model.ComprobanteElectronico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ComprobanteElectronicoRepository extends JpaRepository<ComprobanteElectronico, Long> {
    Optional<ComprobanteElectronico> findByVentaIdVenta(Integer idVenta);
}
