package com.comercialmoises.repository;

import com.comercialmoises.model.MovimientoInventario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovimientoInventarioRepository extends JpaRepository<MovimientoInventario, Integer> {
}
