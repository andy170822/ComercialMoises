package com.comercialmoises.repository;

import com.comercialmoises.model.AuditoriaLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditoriaLogRepository extends JpaRepository<AuditoriaLog, Long> {
}
