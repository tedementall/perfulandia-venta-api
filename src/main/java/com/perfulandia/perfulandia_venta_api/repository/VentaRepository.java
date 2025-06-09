package com.perfulandia.perfulandia_venta_api.repository;

import com.perfulandia.perfulandia_venta_api.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {
}
