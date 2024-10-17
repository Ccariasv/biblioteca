package com.umg.biblioteca.repository;

import com.umg.biblioteca.model.DetallePrestamo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetallePrestamoRepository extends JpaRepository<DetallePrestamo, Long> {
}