package com.comercialmoises.repository;

import com.comercialmoises.model.DetalleVenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.comercialmoises.model.Venta;
import java.util.List;

@Repository
public interface DetalleVentaRepository extends JpaRepository<DetalleVenta, Integer> {
    List<DetalleVenta> findByVenta(Venta venta);
}
