package com.comercialmoises.repository;

import com.comercialmoises.model.Venta;
import com.comercialmoises.dto.ProductoMasVendidoDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Integer> {
    @Query("SELECT new com.comercialmoises.dto.ProductoMasVendidoDTO(p.nombreProducto, SUM(d.cantidad)) " +
            "FROM DetalleVenta d JOIN d.producto p GROUP BY p.nombreProducto ORDER BY SUM(d.cantidad) DESC")
    List<ProductoMasVendidoDTO> findTopSellingProducts(Pageable pageable);

    List<Venta> findByCliente_IdClienteOrderByFechaVentaDesc(Integer idCliente);
}
